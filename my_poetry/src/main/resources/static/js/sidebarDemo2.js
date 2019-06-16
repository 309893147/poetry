(function () {
    var Sidebar = function (eId, closeBarId) {
        this.state = 'opened';
        this.el = document.getElementById(eId || 'sidebar');
        this.closeBarEl = document.getElementById(closeBarId || 'closeBar');
        //闭包
        var self = this;
        this.el.addEventListener('click', function (event) {
            if (event.target !== self.el) {
                self.triggerSwitch();
            }
        });
    };

    Sidebar.prototype.close = function () {

        this.el.style.left = '0';
        this.el.className = 'move_left';
        this.closeBarEl.style.left = '0';
        this.closeBarEl.className = 'closeBar_move_right';
        this.state = 'closed';

    };
    Sidebar.prototype.open = function () {
        this.el.style.left = '-120px';
        this.el.className = 'move_right';
        this.closeBarEl.style.left = '160px';
        this.closeBarEl.className = 'closeBar_move_left';
        this.state = 'opened';

    };
    Sidebar.prototype.triggerSwitch = function () {
        if (this.state === 'opened') {
            this.close();
        } else {
            this.open();
        }
    };
    var sideBar = new Sidebar();
    //菜单项的构造函数

//立即执行,匿名函数
})();