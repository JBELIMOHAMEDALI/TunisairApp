<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    require_once "../db.php";
    $from = $_GET['from'];
    $to=$_GET['to'];
    $description=$_GET['description'];
    $matricule =$_GET['matricule'];
    $sql="INSERT INTO `authoriazation`(`from`, `to`, `description`, `matricule`) VALUES ('$from','$to','$description','$matricule');";
    $res=$db->exec($sql);
    if($res){
        $sql2="UPDATE `user` SET authorizationbalance=0 WHERE user.matricule=".$matricule;
        $res2=$db->exec($sql2);
        if($res2)
        {echo json_encode($res2);}
    }
    else {return false;}
    //var_dump($res);
    //echo"sucess insert for student";}else
    //{echo"no method  post";
} else {
    echo "Method not supported !";
}
?>