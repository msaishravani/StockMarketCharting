var app = angular.module("CompanyManagement", []);
 
// Controller Part
app.controller("CompanyController", function($scope, $http) {
 
	// Now load the data from server
    _refreshCompanyData();
    
    $scope.companys = [];
    $scope.companyForm = {
        name:"",
	    turnover:"",
	    ceo:"",
	    directors:"",
	    sector:"",
	    description:"",
	    status:"",
    };
 
    //cleaning form data
    $scope.createCompany = function() {
        _clearFormData();
    }
 
    // Clear the form
    function _clearFormData() {
    	
    	$scope.companyForm.name = company.name;
        $scope.companyForm.turnover = company.turnover
        $scope.companyForm.ceo = company.ceo;
        $scope.companyForm.directors = company.directors;
        $scope.companyForm.sector = company.sector
        $scope.companyForm.description = company.description;
        $scope.companyForm.status = company.status;
    };
    
    $window.onload = function(e) {
    	alert("on load")
    	$http({
            method: 'GET',
            url: 'http://localhost:2525/company/'
        }).then(
            function(res) { // success
                $scope.companys = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
    // get companys
    function _refreshCompanyData() {
        $http({
            method: 'GET',
            url: 'http://localhost:2525/company/'
        }).then(
            function(res) { // success
                $scope.companys = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
    //add company
    $scope.submitCompany = function() {
 
        var method = "POST";
        var url = 'http://localhost:2525/company/save';
        
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.companyForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
    
    // edit company
    $scope.editCompany = function(company) {
    	
        $scope.companyForm.id = company.id;
        $scope.companyForm.name = company.name;
        $scope.companyForm.turnover = company.turnover
        $scope.companyForm.ceo = company.ceo;
        $scope.companyForm.directors = company.directors;
        $scope.companyForm.sector = company.sector
        $scope.companyForm.description = company.description;
        $scope.companyForm.status = company.status;
        
    };
    
    // delete company
    $scope.deleteCompany = function(company) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:2525/company/delete/' + company.pid
        }).then(_success, _error);
    };
 
    //while request success
    function _success(res) {
        _refreshCompanyData();
        _clearFormData();
    }
    
    // while request error
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
    
});