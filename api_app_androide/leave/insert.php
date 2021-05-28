<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    require_once "../db.php";
    $from = $_GET['from'];
    $to=$_GET['to'];
    $description=$_GET['description'];
    $justification=$_GET['justification'];
    $year=date("Y");
    $matricule =$_GET['matricule'];
    $id_type_leave =$_GET['id_type_leave'];

    $sql="INSERT INTO `leavee`( `from`, `to`, `description`, `justification`, `year`, `matricule`, `id_type_leave`) 
	values('$from','$to','$description','$justification','$year',$matricule,$id_type_leave);";
    $res=$db->exec($sql);


   if($res){echo "don";
       $date1 = strtotime($from);
       $date2 = strtotime($to);
       $diff = abs($date2 - $date1);
       $years = floor($diff / (365*60*60*24));
       $months = floor(($diff - $years * 365*60*60*24)
           / (30*60*60*24));
       $days = floor(($diff - $years * 365*60*60*24 -
               $months*30*60*60*24)/ (60*60*24));
       $sql2="UPDATE `user` SET leavebalance=leavebalance-".$days." WHERE user.matricule=".$matricule;
       $res2=$db->exec($sql2);
       if($res2)
       {echo json_encode($res);}
   }
   else{echo "error";}

} else {
    echo "Method not supported !";
}
?>