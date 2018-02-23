function deleteItem(e,t) {
    console.log("ffffffffffffffffffffffff")
    var rows = document.getElementById("table").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
    console.log(rows);
    let doIt=false;
    if(rows===2){
       doIt=confirm("It is the last  item  in your order! If you  delete  it you will be redirected  to the start  page. Are  you sure?");
        if(doIt) {
            let tr=document.getElementById(e.value);
            tr.parentNode.removeChild(tr);
        }else{
            t.preventDefault();
        }
    }else{
        let tr=document.getElementById(e.value);
        tr.parentNode.removeChild(tr);
    }
        let delet = document.getElementById("delete");
        delet.value = "delete";
        putToLocalStorage();
        //putToLocalStorage();
}
function back(e) {
    let back=document.getElementById("goBack");
    back.value="back";
    putToLocalStorage();
}

function putToLocalStorage(event) {
    let coffeeId=document.getElementsByName("coffeeId");
    let toSendID=[];
    for(let i=0;i<coffeeId.length;i++){
        toSendID.push(coffeeId[i].value);
    }
    let toSendCoffeeId=document.getElementById("toSendCoffeeId");
    toSendCoffeeId.value=toSendID;

    let coffeeQuantity=document.getElementsByName("coffeeQuantity");
    let toSendQUA=[];
    var reg = new RegExp('^[0-9]+$');
    let error=false;
    for(let i=0;i<coffeeQuantity.length;i++){
        if(coffeeQuantity[i].value<1 || !reg.test(coffeeQuantity[i].value)){
            coffeeQuantity[i].style.color="red";
            error=true;
        }
        toSendQUA.push(coffeeQuantity[i].value);
    }
    let toSendQuantity=document.getElementById("toSendQuantity");
    toSendQuantity.value=toSendQUA;
    if(error){
        alert("Sorry! Quantity is less 0 or not number");
        event.preventDefault();
    }else{
        document.getElementsByTagName("form")[0].submit();
    }

}
