// 购物车页面交互脚本
(function() {
    // 更新购物车数量（ajax方式，可选）
    function updateQuantity(cartId, quantity) {
        if (quantity < 1) {
            if (confirm('确定要删除该商品吗？')) {
                window.location.href = '/cart/remove?cartId=' + cartId;
            }
            return;
        }
        // 使用表单提交方式（简单可靠）
        var form = document.getElementById('cart-update-form-' + cartId);
        if (form) {
            form.querySelector('[name="quantity"]').value = quantity;
            form.submit();
        }
    }

    // 绑定数量输入框的change事件
    var quantityInputs = document.querySelectorAll('.cart-quantity-input');
    for (var i = 0; i < quantityInputs.length; i++) {
        quantityInputs[i].addEventListener('change', function(e) {
            var input = e.target;
            var cartId = input.getAttribute('data-cart-id');
            var quantity = parseInt(input.value, 10);
            if (isNaN(quantity) || quantity < 1) {
                input.value = 1;
                quantity = 1;
            }
            updateQuantity(cartId, quantity);
        });
    }

    // 删除确认
    var deleteBtns = document.querySelectorAll('.cart-delete-btn');
    for (var j = 0; j < deleteBtns.length; j++) {
        deleteBtns[j].addEventListener('click', function(e) {
            if (!confirm('确定要从购物车删除该商品吗？')) {
                e.preventDefault();
            }
        });
    }
})();