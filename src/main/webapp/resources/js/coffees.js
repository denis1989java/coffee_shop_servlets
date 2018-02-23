let coffeeId=[];
let quantity=[];

function addQuantity(t,event) {
    var reg = new RegExp('^[0-9]+$');
    var e = document.getElementById(t.value);
    if(e.value<1 || !reg.test(e.value)){
        alert("wrong quantity");
        e.value=1;
        return;
    }
    if (coffeeId.indexOf(t.value)>-1){
        quantity[coffeeId.indexOf(t.value)]=(parseInt(quantity[coffeeId.indexOf(t.value)]) + parseInt(e.value));
    }else{
        quantity.push(e.value);
        coffeeId.push(t.value);
    }
    let toSendCoffeeId=document.getElementById("toSendCoffeeId");
    toSendCoffeeId.value=coffeeId;
    let toSendQuantity=document.getElementById("toSendQuantity");
    toSendQuantity.value = quantity;
    let i=event.target.parentNode.childNodes[3];
    i.style.opacity="1";
    $(i).animate({opacity: 0}, 500);
    let basket=document.getElementById("added");
    basket.value="added";
    setTimeout(function () {
        document.getElementsByTagName("form")[0].submit();
    },500)
}
function check(event) {
    let basket=document.getElementById("basket");
    let d=basket.innerHTML;
     if(parseInt(d)===0){
        event.preventDefault();
        alert('No items have been selected');
    }else{
         let basket=document.getElementById("added");
         basket.value="";
     }
}

