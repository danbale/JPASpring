myApp.controller('homeAppController', function ($scope,userService) {
  $scope.userList = {};
  $scope.user = {};
  $scope.userListt=function(){
		  userService.userList()
	      .then(
		      function(d  ) {
		    	  $scope.userList = angular.copy(d);
		    	  
		      },
		      function(errResponse){
		          console.error('Error while fetching Users');
		      }
	  );
  }

 
});
myApp.factory('userService',function($http,$q){
	var service={
			userList:userList,
	};
	var URL='http://localhost:8080/jpaproject/User'
	function userList(){
		var deferred = $q.defer();
        $http.get(URL+'/ListUsers')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error(errResponse);
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
	}
	
	
	
	function editUser(list){
		
	}

	return service;
});
