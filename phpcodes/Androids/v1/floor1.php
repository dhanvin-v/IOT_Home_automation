<?php
require_once '../includes/DbOperations.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['light1']) and isset($_POST['light2']) and isset($_POST['sensors1']) and isset($_POST['sensors2']) and isset($_POST['motiondetector'])){
		$db = new DbOperations();
		if($db->updateFirstFloor($_POST['light1'], $_POST['light2'], $_POST['sensors1'], $_POST['sensors2'], $_POST['motiondetector'])){
			$response['error']= false;
			$response['message'] = "Update Success";
		}
		else{
				$response['error']= true;
				$response['message']="Update Failed ";
			}
		}
		else {
				$response['error']= true;
				$response['message']="Required Fields missing";
		}
}
else{
	$response['error']= true;
	$response['message']="Invalid Request";
}
echo json_encode($response);
?>
