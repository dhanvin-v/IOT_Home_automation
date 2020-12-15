<?php 

	class DbOperations{

		private $con; 

		function __construct(){

			require_once dirname(__FILE__).'/DbConnect.php';

			$db = new DbConnect();

			$this->con = $db->connect();

		}

		/*CRUD -> C -> CREATE */

		public function createUser($username, $pass){
			if($this->isUserExist($username)){
			
				return 0; 
			}else{
				$password = md5($pass);
				//$password = $pass;
				$stmt = $this->con->prepare("INSERT INTO users (`username`,`password`) VALUES (?, ?);");
				$stmt->bind_param("ss",$username,$password);
				if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
			}
		}

		public function userLogin($username, $pass){
			$password = md5($pass);
			$stmt = $this->con->prepare("SELECT id FROM users WHERE username = ? AND password = ?");
			$stmt->bind_param("ss",$username,$password);
			$stmt->execute();
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}
		

		public function getUserByUsername($username){
			$stmt = $this->con->prepare("SELECT * FROM users WHERE username = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		
				
		
		public function updateThermostat($temperature,$fan,$mode){
			$stmt = $this->con->prepare("DELETE FROM Thermostat1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO Thermostat1 (`temperature`,`fan`,`mode`) VALUES (?, ?, ?);");
			$stmt->bind_param("sss",$temperature,$fan,$mode);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}

		private function isUserExist($username){
			$stmt = $this->con->prepare("SELECT id FROM users WHERE username = ?");
			$stmt->bind_param("s", $username);
			$stmt->execute(); 
			$stmt->store_result(); 
			return $stmt->num_rows > 0; 
		}
		public function updateFirstFloor($light1, $light2, $sensors1, $sensors2, $motiondetector){
			$stmt = $this->con->prepare("DELETE FROM firstfloor1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO firstfloor1 (`light1`,`light2`,`sensors1`,`sensors2`,`motiondetector`) VALUES (?, ?, ?, ?, ?);");
			$stmt->bind_param("sssss",$light1, $light2, $sensors1, $sensors2, $motiondetector);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
 		
		public function updateSecondFloor($light1, $light2, $sensor1, $sensor2, $motiondetector){
			$stmt = $this->con->prepare("DELETE FROM secondfloor1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO secondfloor1 (`light1`,`light2`,`sensor1`,`sensor2`,`motiondetector`) VALUES (?, ?, ?, ?, ?);");
			$stmt->bind_param("sssss",$light1,$light2,$sensor1,$sensor2,$motiondetector);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}

		public function updateTherm($temp, $fan, $mode){
			$stmt = $this->con->prepare("DELETE FROM therm1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO therm1 (`temp`,`fan`,`mode`) VALUES ( ?, ?, ?);");
			$stmt->bind_param("sss",$temp,$fan,$mode);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}

		
		public function updateLocks($frontdoor, $garagedoor, $backdoor){
			$stmt = $this->con->prepare("DELETE FROM locks1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO locks1 (`frontdoor`,`garagedoor`,`backdoor`) VALUES ( ?, ?, ?);");
			$stmt->bind_param("sss",$frontdoor,$garagedoor,$backdoor);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}

		public function updateThermostats($temperature, $fan, $mode){
			$stmt = $this->con->prepare("DELETE FROM Thermostat1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO Thermostat1 (`temperature`,`fan`,`mode`) VALUES ( ?, ?, ?);");
			$stmt->bind_param("sss",$temperature,$fan,$mode);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
		
		public function updateGarageDoor($garagetwodoor, $garageonedoor){
			$stmt = $this->con->prepare("DELETE FROM garagedoor1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO garagedoor1 (`garagetwodoor`,`garageonedoor`) VALUES ( ?, ?);");
			$stmt->bind_param("ss",$garagetwodoor,$garageonedoor);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
		
		public function updateSecurity($armedstatus){
			$stmt = $this->con->prepare("DELETE FROM security1;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO security1 (`armedstatus`) VALUES ( ?);");
			$stmt->bind_param("s",$armedstatus);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
		
		public function updateFirstFloorsensor($sensor1,$sensor2){
			$stmt = $this->con->prepare("DELETE FROM firstsensorfloor;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO firstsensorfloor (`sensor1`,`sensor2`) VALUES (?, ?);");
			$stmt->bind_param("ss",$sensor1,$sensor2);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
		
		public function updateSecondFloorsensor($sensor1,$sensor2){
			$stmt = $this->con->prepare("DELETE FROM secondsensorfloor;");
			$stmt->execute();
			$stmt = $this->con->prepare("INSERT INTO secondsensorfloor (`sensor1`,`sensor2`) VALUES (?, ?);");
			$stmt->bind_param("ss",$sensor1,$sensor2);
			if($stmt->execute()){
					return 1; 
				}else{
					return 2; 
				}
		}
		
		public function getsensorsfirst(){
			$stmt = $this->con->prepare("select * FROM firstsensorfloor;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		
		public function getsensorssecond(){
			$stmt = $this->con->prepare("select * FROM secondsensorfloor;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}	

		public function getthermostat(){
			$stmt = $this->con->prepare("select * FROM therm1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}	

		public function getfirstfloor(){
			$stmt = $this->con->prepare("select * FROM firstfloor1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getsecondfloor(){
			$stmt = $this->con->prepare("select * FROM secondfloor1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getsecurity(){
			$stmt = $this->con->prepare("select * FROM security1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getgaragedoor(){
			$stmt = $this->con->prepare("select * FROM garagedoor1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getlocks(){
			$stmt = $this->con->prepare("select * FROM locks1;");
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		

	}
?>
