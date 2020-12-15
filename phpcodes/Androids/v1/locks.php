<?php
require_once '../includes/DbOperations.php';
$response = array();
if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['frontdoor']) and isset($_POST['garagedoor']) and isset($_POST['backdoor'])){
		$db = new DbOperations();
		if($db->updateLocks($_POST['frontdoor'], $_POST['garagedoor'], $_POST['backdoor'])){
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
