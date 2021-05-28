<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    include "./db.php";
    $matrcule=$_GET["matrcule"];
    $query=$db->prepare("SELECT user.leavebalance,user.authorizationbalance FROM user WHERE user.matricule=".$matrcule);
    $res=$query->execute();

    if($query->rowCount()>0){
        $data=$query->fetchAll(PDO::FETCH_ASSOC);
      //  print_r($data."-------");
        echo json_encode($data);
        //$data[0]['leavebalance']
        //$data[0]['authorizationbalance']
    } else {
        $json['success']=0;
        $json['message']='data not found';
        $json['myintro']='';
        echo json_encode($json);
    }
}else {
    echo "Method not supported !";
}
//affected-row
?>