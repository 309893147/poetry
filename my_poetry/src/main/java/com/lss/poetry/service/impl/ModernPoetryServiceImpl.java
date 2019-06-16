package com.lss.poetry.service.impl;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.lss.poetry.mapper.ModernPoetryMapper;

import com.lss.poetry.pojo.ModernPoetry;

import com.lss.poetry.service.ModernPoetryService;

import com.lss.poetry.utils.PagedResult;

@Service
public class ModernPoetryServiceImpl implements ModernPoetryService {

    @Autowired
    private ModernPoetryMapper modernPoetryMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;//k-v都是对象
//
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;//操作字符串

//	   @Scheduled(fixedRate = 1000 * 30)
//	    public void reportCurrentTime(){
//	        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
//	    }
//
//	    //每1分钟执行一次
//	    @Scheduled(cron = "0 */1 *  * * * ")
//	    public void reportCurrentByCron(){
//	        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
//	    }
//
//	    private SimpleDateFormat dateFormat(){
//	        return new SimpleDateFormat ("HH:mm:ss");
//	    }
//

    //	@Autowired
//	 private MpRepository mpRepository;
    // 联合现代诗歌所有信息
    public List<ModernPoetry> getAllMp() {

//		//查询缓存
//		List<ModernPoetry> mpList=(List<ModernPoetry>) redisTemplate.opsForValue().get("allMp");

//		if(null==mpList) {
//			mpList=modernPoetryMapper.selectByExample(null);
//			redisTemplate.opsForValue().set("allMp",mpList);
//		}

        return modernPoetryMapper.getMpDesc();

    }

    //添加现代诗歌
    @Override
    public int addMp(@Valid ModernPoetry modernPoetry) {

        return modernPoetryMapper.insertSelective(modernPoetry);
    }

    @Override
    public Integer addViews(Integer views, Integer mpId) {

        // 设置每个键值的key为mp的Id

        String keyMpId = "mpviews_"+ String.valueOf(mpId);
        System.out.println(keyMpId);
        // 字符串的序列化器

//		RedisSerializer redisSerializer = new StringRedisSerializer();
//		redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setValueSerializer(redisSerializer);
		//opsForValue 操作字符串 opsForList 列表
		Integer mpViews= (Integer) redisTemplate.opsForValue().get(keyMpId);
		if(null == mpViews) {

			redisTemplate.opsForValue().set(keyMpId,views);
		}else {
			redisTemplate.opsForValue().set(keyMpId,mpViews+1);
		}

		Integer newMpViews=(Integer)redisTemplate.opsForValue().get(keyMpId);

            //更新缓存数据到数据库
       // modernPoetryMapper.updateViews(newMpViews + 1, mpId);
        modernPoetryMapper.updateViews(newMpViews,mpId);
       System.out.println("mpkey缓存:" + newMpViews);

        return newMpViews;
    }

    @Override
    public PagedResult getAllModernPoetry(Integer page, Integer pageSize) {

//        int[] a = new int[]{-1, -1, -1, -1};//初始化数组
//        Random random = new Random();
//        int count = 0;//记录有效的随机数个数
//        while (count < a.length) {
//            boolean flag = true;//用来标志的变量
//            int r = random.nextInt(10);
//            for (int i = 0; i < a.length; i++) {
//                if (r == a[i]) {
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag) {
//                a[count] = r;
//                System.out.println(r);
//                count++;
//            }
//        }
        PageHelper.startPage(page, pageSize);
        // 字符串的序列化器
//		RedisSerializer redisSerializer = new StringRedisSerializer();
//		redisTemplate.setKeySerializer(redisSerializer);
//
//        redisTemplate.setValueSerializer(redisSerializer);

		// 高并发条件下,此处有:缓存穿透问题
		// 查询缓存
//		List<ModernPoetry> mpList = (List<ModernPoetry>) redisTemplate.opsForValue().get("pageMp"+page);
//		// 加锁锁定 springbean 是单例对象
//		// 双重检测锁
//		if (null == mpList) {
//			synchronized (this) {
//				// 在走一次redis
//				mpList = (List<ModernPoetry>) redisTemplate.opsForValue().get("pageMp"+page);
//
//				if (null == mpList) {
//					System.out.println("查询的数据库...");
//					mpList = modernPoetryMapper.selectByExample(null);
//					redisTemplate.opsForValue().set("pageMp"+page, mpList);
//				} else {
//					System.out.println("查询的缓存...");
//				}
//			}
//
//		} else {
//			System.out.println("查询的缓存...");
//		}

//		return mpList;



     List<ModernPoetry> mpList = modernPoetryMapper.getMpDesc();

        PageInfo<ModernPoetry> pageList = new PageInfo<>(mpList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(mpList);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    @Override
    public ModernPoetry getModernPoetryById(Integer id) {
//        RedisSerializer redisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setValueSerializer(redisSerializer);
        ModernPoetry modernPoetry = (ModernPoetry) redisTemplate.opsForValue().get("mp_" + id);
        System.out.println("查询的缓存..."+11);
        // 如果缓存没有则到数据库查询并放入缓存         
        if (modernPoetry == null) {
            modernPoetry = modernPoetryMapper.selectByPrimaryKey(id);
            System.out.println("查询的数据库..."+22);
            redisTemplate.opsForValue().set("mp_"+id,modernPoetry);
    }//   return article;

//        ModernPoetry modernPoetry = modernPoetryMapper.selectByPrimaryKey(id);
        return modernPoetry;
    }

    // 超链接的现代诗 进行修改
    public void updateMp(ModernPoetry modernPoetry) {

        modernPoetryMapper.updateByPrimaryKeySelective(modernPoetry);
    }

    @Override
    public List<ModernPoetry> randomQueryMp() {

        List<ModernPoetry> list = modernPoetryMapper.randomQueryMp();
//        System.out.println(list);
        return list;
    }

    @Override
    public List<ModernPoetry> getMpByPinYin(String pinYin) {

        List<ModernPoetry> list = modernPoetryMapper.getMpByPinYin(pinYin);
        return list;
    }

    @Override
    public void deleteMp(Integer mpId) {
        // TODO Auto-generated method stub
        modernPoetryMapper.deleteByPrimaryKey(mpId);
    }
//	@Override
//	public Object addViews(ModernPoetry modernPoetry) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//
//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		Date date = new Date();
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("Current  Exec time is:" + sf.format(date));
//		System.out.println("hell");
//		addViews(views, mpId);
//		modernPoetryMapper.updateViews(Integer a,Integer mpId);
//	}

    /*
     * @Override public List<ModernPoetry> queryOwnCollect(String uId) {
     * List<ModernPoetry> list=modernPoetryMapperCustom.queryOwnCollect(uId); return
     * list;
     *
     * }
     */

}
