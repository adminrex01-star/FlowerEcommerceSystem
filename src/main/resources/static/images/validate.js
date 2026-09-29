// 前端表单校验通用函数
(function() {
    // 注册表单校验
    var registerForm = document.querySelector('form[action="/register"]');
    if (registerForm) {
        registerForm.addEventListener('submit', function(e) {
            var username = registerForm.querySelector('[name="username"]');
            var password = registerForm.querySelector('[name="password"]');
            var phone = registerForm.querySelector('[name="phone"]');
            var email = registerForm.querySelector('[name="email"]');

            if (!username.value.trim()) {
                alert('用户名不能为空');
                e.preventDefault();
                return false;
            }
            if (username.value.length < 3 || username.value.length > 20) {
                alert('用户名长度应为3-20个字符');
                e.preventDefault();
                return false;
            }
            if (!password.value) {
                alert('密码不能为空');
                e.preventDefault();
                return false;
            }
            if (password.value.length < 3) {
                alert('密码长度至少3位');
                e.preventDefault();
                return false;
            }
            if (phone.value && !/^1[3-9]\d{9}$/.test(phone.value)) {
                alert('请输入正确的手机号码');
                e.preventDefault();
                return false;
            }
            if (email.value && !/^\S+@\S+\.\S+$/.test(email.value)) {
                alert('请输入正确的邮箱地址');
                e.preventDefault();
                return false;
            }
            return true;
        });
    }

    // 登录表单校验
    var loginForm = document.querySelector('form[action="/login"]');
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            var username = loginForm.querySelector('[name="username"]');
            var password = loginForm.querySelector('[name="password"]');
            if (!username.value.trim()) {
                alert('请输入用户名');
                e.preventDefault();
                return false;
            }
            if (!password.value) {
                alert('请输入密码');
                e.preventDefault();
                return false;
            }
            return true;
        });
    }

    // 个人信息修改校验
    var profileForm = document.querySelector('form[action="/profile/update"]');
    if (profileForm) {
        profileForm.addEventListener('submit', function(e) {
            var phone = profileForm.querySelector('[name="phone"]');
            var email = profileForm.querySelector('[name="email"]');
            if (phone.value && !/^1[3-9]\d{9}$/.test(phone.value)) {
                alert('手机号码格式不正确');
                e.preventDefault();
                return false;
            }
            if (email.value && !/^\S+@\S+\.\S+$/.test(email.value)) {
                alert('邮箱格式不正确');
                e.preventDefault();
                return false;
            }
            return true;
        });
    }

    // 修改密码校验
    var pwdForm = document.querySelector('form[action="/profile/password"]');
    if (pwdForm) {
        pwdForm.addEventListener('submit', function(e) {
            var oldPwd = pwdForm.querySelector('[name="oldPassword"]');
            var newPwd = pwdForm.querySelector('[name="newPassword"]');
            if (!oldPwd.value || !newPwd.value) {
                alert('请完整填写原密码和新密码');
                e.preventDefault();
                return false;
            }
            if (newPwd.value.length < 3) {
                alert('新密码长度不能少于3位');
                e.preventDefault();
                return false;
            }
            return true;
        });
    }
})();