counter = 0;

const testInputJson = [
    {
        "productId": 1,
        "productName": "Hats",
        "userId": 1,
        "username": "Pavel",
        "discount": "10%"
    },
    {
        "productId": 2,
        "productName": "Socks",
        "userId": 4,
        "username": "Pavel",
        "discount": "40%"
    },
    {
        "productId": 5,
        "productName": "Bitches",
        "userId": 12,
        "username": "Pavel",
        "discount": "17%"
    }
];

const users = [
    {
        "userId": 1,
        "username": "Pavel",
    },
    {
        "userId": 2,
        "username": "Alex",
    },
    {
        "userId": 3,
        "username": "Masha",
    },
    {
        "userId": 4,
        "username": "Another Freak"
    }
];

$(document).ready(function () {
    renderSelect(testInputJson, "productChoosing");
    renderSelect(testInputJson, "userProductChoosing");
    renderUsersSelect(users);
    renderTable(testInputJson);
});

function renderTable(payload) {
    payload.forEach(function (t) {
        let color = parseInt(t.discount) > 30 ? "red" : "green";
        $(`<tr id=${counter++}>` +
            `<th scope=\"row\"><input type="checkbox" id="${t.productId}/${t.userId}"/></th>` +
            `<th>${t.productName}</th>` +
            `<th>${t.username}</th>` +
            `<th><span style='color: ${color};'>${t.discount}</span></th>` +
            "</tr>").insertAfter($("tr:first"));
    });
}

function renderSelect(payload, action) {
    payload.forEach(function (t) {
        $(`#${action}`).append($(`<option value="${t.productId}">${t.productName}</option>`));
    });
}

function updateDiscountModel(action, user) {
    let model = [];
    let actionCheck = action === "userProductChoosing";
    $(`#${action}`).find(":selected").each(function () {
        model.push({
            productId: this.value,
            productName: this.text,
            userId: actionCheck ? user.id : null,
            username: actionCheck ? user.username : "ALL Users",
            discount: actionCheck ? user.discount : `${$("#discountProductInput").val()}%`
        });
        testInputJson.push({
            productId: this.value,
            productName: this.text,
            userId: actionCheck ? user.id : null,
            username: actionCheck ? user.username : "ALL Users",
            discount: actionCheck ? user.discount : `${$("#discountProductInput").val()}%`
        });
    });
    renderTable(model);
}

function userProductUpdate() {
    $(`#userChoosing`).find(":selected").each(function () {
        updateDiscountModel('userProductChoosing', {
            id: this.value,
            username: this.text,
            discount: `${$("#discountUserProductInput").val()}%`
        })
    });
}

function renderUsersSelect(payload) {
    payload.forEach(function (t) {
        $("#userChoosing").append($(`<option value="${t.userId}">${t.username}</option>`));
    });
}

function removeRow() {
    let disList = $("#discountList");
    for(let i = 0; i < disList.find("input:checked").length; i++) {
        let index = testInputJson.findIndex(item => {
            let s = disList.find("input:checked")[i].id.split(' ');
            if(parseInt(s[0]) === item.productId) {
                if(isNaN(parseInt(s[1]))) {
                    return true;
                } else if(item.userId === parseInt(s[1])) {
                    return true;
                }
            }
        });
        testInputJson.splice(index, index);
        document.getElementById(disList.find("input:checked")[i].id).parentNode.parentNode.remove();
    }
}

function getResult() {
    alert(`I have ${testInputJson.length} entries`);
    testInputJson.forEach(item => {
        alert(`
        {"productId": ${item.productId},
        "productName": ${item.productName},
        "userId": ${item.userId},
        "username": ${item.username},
        "discount": ${item.discount}}`);
    })
}