    //得到行对象
    function getRowObj(obj)
    {
    var i = 0;
    while(obj.tagName.toLowerCase() != "tr"){
        obj = obj.parentNode;
        if(obj.tagName.toLowerCase() == "table")return null;
    }
    return obj;
    }
    //根据得到的行对象得到所在的行数
    function getRowNo(obj){
    var trObj = getRowObj(obj);
    var trArr = trObj.parentNode.children;
    for(var trNo= 0; trNo < trArr.length; trNo++){
        if(trObj == trObj.parentNode.children[trNo]){
            alert(trNo+1);
    }
    }
    }
    //删除行
    function delRow(obj){
    if(confirm("确定删除么?")){
        var tr = this.getRowObj(obj);
        if(tr != null){
            tr.parentNode.removeChild(tr);
        }else{
            throw new Error("the given object is not contained by the table");
        }
    }
    }