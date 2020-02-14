var app = angular.module("sales-management", [ "ngRoute" ]);
var jq = $.noConflict();
app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : "main.html"
	}).when("/addTax", {
		templateUrl : "taxDetail.html",
		controller : 'addtax'
	}).when("/addVendor", {
		templateUrl : "vendorDetails.html",
		controller : 'addVendor'
	}).when("/addProduct", {
		templateUrl : "addProduct.html",
		controller : 'productController'
	}).when("/updateProduct", {
		templateUrl : "updateProduct.html",
		controller : 'productController'
	}).when("/addRoute", {
		templateUrl : "AddRoute.html",
		controller : 'addRoute'
	}).when("/addRetailer", {
		templateUrl : "AddCustomer.html",
		controller : 'customerController'
	}).when("/updateRetailer", {
		templateUrl : "updateCustomer.html",
		controller : 'customerController'
	}).when("/addInventory", {
		templateUrl : "addInventory.html",
		controller : 'inventoryController'
	}).when("/updateInventory", {
		templateUrl : "updateInventory.html",
		controller : 'inventoryController'
	}).when("/viewInventory", {
		templateUrl : "inventoryStatus.html",
		controller : 'inventoryController'
	}).when("/addInvoice", {
		templateUrl : "addInvoice.html",
		controller : 'invoiceController'
	}).when("/viewInvoice", {
		templateUrl : "viewInvoice.html",
		controller : 'invoiceController'
	}).when("/reports", {
		templateUrl : "report.html",
		controller : 'reportController'
	}).when("/dailyColletion", {
		templateUrl : "collection.html",
		controller : 'collectionController'
	}).when("/#addInory", {
		templateUrl : "blue.htm"
	});
});

app.controller("addtax", function($scope, $http) {
	$scope.master = {};
	$scope.addTax = function() {
		$http({
			method : "POST",
			url : "/tax/addTax",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};

});

app.controller("addRoute", function($scope, $http) {
	$scope.master = {};
	$scope.addRoute = function() {
		$http({
			method : "POST",
			url : "/route/addRoute",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};

});

app.controller("addVendor", function($scope, $http) {
	$scope.master = {};
	$scope.addVendor = function() {
		$http({
			method : "POST",
			url : "/vendor/addVendor",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};

});

app.controller('productController', function($scope, $http) {
	$http.get('/vendor/getAll').success(function(data) {
		$scope.vendors = data;
	});
	$http.get('/tax/getAll').success(function(data) {
		$scope.taxes = data;
	});

	$scope.addProduct = function() {
		$scope.master = {};
		$scope.form.taxPecent = $scope.selectedTax.taxPercent;
		$scope.form.vendorID = $scope.selectedVendor.id;
		$scope.form.productCode = $scope.form.productName.replace(/ /g, "");

		$http({
			method : "POST",
			url : "/product/addProduct",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};
	$scope.updateProduct = function() {
		$scope.master = {};
		$scope.selectedProduct.taxPecent = $scope.selectedTax.taxPercent;
		$scope.selectedProduct.sellPrice = $scope.form.sellPrice;
		$scope.selectedProduct.costPrice = $scope.form.costPrice;
		$scope.selectedProduct.mrp = $scope.form.mrp;
		$scope.selectedProduct.hsnCode = $scope.form.hsnCode;

		$http({
			method : "PUT",
			url : "/product/updateProduct",
			data : angular.toJson($scope.selectedProduct),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
			$scope.selectedProduct = angular.copy($scope.master);
		});
	};

	$scope.getproduct = function() {
		var id = $scope.selectedVendor.id;
		$http.get('/product/getByVendor/' + id).success(function(data) {
			$scope.products = data;
		})
	};
});

app.controller('customerController', function($scope, $http) {

	$http.get('/route/getAll').success(function(data) {
		$scope.routes = data;
	})
	$scope.getRetailers = function() {
		var id = $scope.selectedRoute.id;
		$http.get('/customer/getAll/' + id).success(function(data) {
			$scope.retailers = data;
		})
	};
	$scope.addCustomer = function() {

		if ($scope.form.gstNumber == null && $scope.form.panNumber == null
				&& $scope.form.addharNumber == null) {
			alert('Please provide GST NO. or PAN No. or ADDHAAR NO.');
			return;
		}
		$scope.master = {};
		$scope.form.route_No = $scope.selectedRoute.id;
		$http({
			method : "POST",
			url : "/customer/addCustomer",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};
	$scope.updateCustomer = function() {
		$scope.master = {};
		$scope.selectedRetailer.gstNumber = $scope.form.gstNumber;
		$scope.selectedRetailer.panNumber = $scope.form.panNumber;
		$scope.selectedRetailer.addharNumber = $scope.form.addharNumber;

		$http({
			method : "PUT",
			url : "/customer/updateCustomer",
			data : angular.toJson($scope.selectedRetailer),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
		});
	};
});
app.controller('inventoryController', function($scope, $http) {
	$scope.master = {};
	$http.get('/vendor/getAll').success(function(data) {
		$scope.vendors = data;
	})

	$scope.getproduct = function() {
		var id = $scope.selectedVendor.id;
		$http.get('/product/getByVendor/' + id).success(function(data) {
			$scope.products = data;
		})
	};
	$scope.getInventory = function() {
		var id = $scope.selectedVendor.id;
		$http.get('/inventory/getByVendor/' + id).success(function(data) {
			$scope.products = data;
		})
	};

	$scope.addInventory = function() {
		$scope.form.productName = $scope.selectedProduct.productCode;
		$scope.form.vendorID = $scope.selectedProduct.vendorID;
		$scope.form.piecePerCase = $scope.selectedProduct.piecePerCase;
		$http({
			method : "POST",
			url : "/inventory/addProduct",
			data : angular.toJson($scope.form),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form = angular.copy($scope.master);
			$scope.products = angular.copy($scope.master);
		});
	};
	$scope.updateInventory = function() {

		$scope.selectedProduct.caseToAdd = $scope.form.caseAdded;
		$http({
			method : "PUT",
			url : "/inventory/updateProduct",
			data : angular.toJson($scope.selectedProduct),
			headers : {
				'Content-Type' : 'application/json'
			}
		}).then(function(response) {
			$scope.message = response.data;
			$scope.form.caseAdded = 0;
			$scope.selectedProduct = angular.copy($scope.master);
		});
	};
	$scope.getproducts = function() {
		var id = $scope.selectedVendor.id;
		$http.get('/inventory/getProductInventory/' + id).success(
				function(data) {
					$scope.inventoryproducts = data;
				})
	};
});
app
		.controller(
				'invoiceController',
				function($scope, $http) {

					$scope.master = {};
					var taxNumber;

					$scope.getDetail = function() {
						$scope.taxNumber = "";

						if ($scope.selectedRetailer.gstNumber == null) {
							if ($scope.selectedRetailer.panNumber == null) {
								if ($scope.selectedRetailer.addharNumber == null) {
									$scope.taxNumber = "";
								} else {
									$scope.taxNumber = "AADHAAR: "
											+ $scope.selectedRetailer.addharNumber;
								}
							} else {
								$scope.taxNumber = "PAN: "
										+ $scope.selectedRetailer.panNumber;
							}

						} else {
							$scope.taxNumber = "GST: "
									+ $scope.selectedRetailer.gstNumber;
						}
					};

					$http.get('/vendor/getAll').success(function(data) {
						$scope.vendors = data;
					})

					$http.get('/route/getAll').success(function(data) {
						$scope.routes = data;
					})
					$scope.getRetailers = function() {
						var id = $scope.selectedRoute.id;
						$http.get('/customer/getAll/' + id).success(
								function(data) {
									$scope.retailers = data;
								})
					};
					$scope.getproduct = function() {
						var id = $scope.selectedVendor.id;
						$http.get('/inventory/getProductInventory/' + id)
								.success(function(data) {
									$scope.products = data;
								})
					};
					$scope.printToCart = function(printable) {
						var innerContents = document.getElementById(printable).innerHTML;
						var popupWinindow = window
								.open(
										'',
										'_blank',
										'width=600,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
						popupWinindow.document.open();
						popupWinindow.document
								.write('<html><head><link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /></head><body onload="window.print()">'
										+ innerContents + '</html>');
						popupWinindow.document.close();
						$scope.invoiceProduct = angular.copy($scope.master);
						$scope.invoice = angular.copy($scope.master);
						$scope.message = angular.copy($scope.master);
						$scope.form.otherDiscountPercent = 0;
						$scope.invoiceAmount = 0;
						$scope.selectedRetailer = angular.copy($scope.master);
					};
					$scope.addInvoiceProduct = function() {

						if ($scope.selectedRetailer.gstNumber === null
								&& $scope.invoiceAmount > 19999.00) {
							alert(' GST Number Required For Invoice of Rs 20000 ot more');
							return;
						}
						$scope.productForm.productCode = $scope.selectedProduct.productName;

						var a = ($scope.selectedProduct.piecePerCase * $scope.productForm.caseSold)
								+ $scope.productForm.looseQuantity
								+ $scope.productForm.discountedPiece;

						if (a > $scope.selectedProduct.totalQuantity) {
							alert('Available Inventory Less Than Order');
							return;
						}
						if ($scope.productForm.caseSold > $scope.selectedProduct.totalCase) {
							alert('Available Inventory Less Than Order');
							return;
						}
						$http({
							method : "POST",
							url : "/invoice/addProduct",
							data : angular.toJson($scope.productForm),
							headers : {
								'Content-Type' : 'application/json'
							}
						})
								.success(
										function(data) {
											$scope.invoiceProduct = data;

											$http
													.get(
															'/invoice/getInvoiceAmount')
													.success(
															function(data) {
																$scope.invoiceAmount = data;
																if ($scope.selectedRetailer.gstNumber === null
																		&& $scope.invoiceAmount > 19999.00) {
																	alert(' GST Number Required For Invoice of Rs 20000 ot more');
																	return;
																}
															});

											$scope.productForm = angular
													.copy($scope.master);
											$scope.selectedProduct = angular
													.copy($scope.master);
										});
					};

					$scope.removeInvoiceProduct = function() {
						$scope.productForm.productCode = $scope.selectedProduct.productName;
						$http({
							method : "POST",
							url : "/invoice/removeProduct",
							data : angular.toJson($scope.productForm),
							headers : {
								'Content-Type' : 'application/json'
							}
						}).success(
								function(data) {
									$scope.invoiceProduct = data;
									$scope.productForm = angular
											.copy($scope.master);
									$scope.selectedProduct = angular
											.copy($scope.master);
								});
					};

					$scope.addInvoice = function() {

						if ($scope.selectedRetailer.gstNumber === null
								&& $scope.invoiceAmount > 19999.00) {
							alert(' GST Number Required For Invoice of Rs 20000 ot more');
							return;
						}

						var routeId = $scope.selectedRoute.id;
						var vendor_Name = $scope.selectedVendor.vendorName;
						var customerName = $scope.selectedRetailer.firmName;
						var otherDiscount = $scope.form.otherDiscountPercent;
						$http({
							method : "POST",
							url : "/invoice/addInvoice",
							data : {
								"routeId" : routeId,
								"vendor_Name" : vendor_Name,
								"customerName" : customerName,
								"otherDiscountPercent" : otherDiscount
							},
							headers : {
								'Content-Type' : 'application/json'
							}
						})
								.success(
										function(data) {
											$scope.invoice = data;
											$scope.selectedProduct = angular
													.copy($scope.master);
											var id = $scope.selectedVendor.id;
											$http
													.get(
															'/inventory/getProductInventory/'
																	+ id)
													.success(
															function(data) {
																$scope.products = data;
																$scope.message = "Invoice Generated SucessFully";

															})
										})
					};
					$scope.getInvoice = function() {

						/* $scope.getRetailers(); */
						var routeId = $scope.selectedRoute.id;
						var customerName = $scope.selectedRetailer.firmName;

						if (customerName == null) {
							customerName = 'NOT Selected';
						}
						$http.get(
								'/invoice/getAll/' + routeId + '/'
										+ customerName).success(function(data) {
							$scope.activeInvoice = data;
						})
					};

					$scope.fetchInvoice = function() {
						var id = $scope.selectedInvoice.id;
						$http.get('/invoice/getInvoice/' + id).success(
								function(data) {
									$scope.productList = data;
								})
					};
					$scope.closeInvoice = function() {
						$scope.master = {};
						$scope.selectedInvoice.deliveryStatus = 'Close';
						$http({
							method : "PUT",
							url : "/invoice/updateInvoice",
							data : angular.toJson($scope.selectedInvoice),
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function(response) {
							$scope.message = response.data;
							$scope.form = angular.copy($scope.master);
						});
					};
					$scope.cancelInvoice = function() {
						$scope.master = {};
						$scope.selectedInvoice.deliveryStatus = 'Cancel';
						$http({
							method : "PUT",
							url : "/invoice/updateInvoice",
							data : angular.toJson($scope.selectedInvoice),
							headers : {
								'Content-Type' : 'application/json'
							}
						}).then(function(response) {
							$scope.message1 = response.data;
							$scope.form = angular.copy($scope.master);
						});
					};
				});
app
		.controller(
				'reportController',
				function($scope, $http) {

					$http.get('/vendor/getAll').success(function(data) {
						$scope.vendors = data;
					})

					$scope.printToCart = function(printable) {
						var innerContents = document.getElementById(printable).innerHTML;
						var popupWinindow = window
								.open(
										'',
										'_blank',
										'width=900,height=700,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
						popupWinindow.document.open();
						popupWinindow.document
								.write('<html><head><link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /></head><body onload="window.print()">'
										+ innerContents + '</html>');
						popupWinindow.document.close();
					}
					$scope.getDateVal = function() {

						jq(document).ready(
								function() {
									$scope.billDate = document
											.getElementById('billDate').value;
								});

					};

					$scope.getSaleReport = function() {
						var id = $scope.selectedVendor.id;

						var date = $scope.billDate;

						$http.get('/report/getAll/' + date + '/' + id).success(
								function(data) {
									$scope.saleReport = data;
								})
					};

				});

app.controller('collectionController', function($scope, $http) {
	$http.get('/route/getAll').success(function(data) {
		$scope.routes = data;
	})
	$scope.getRouteInvoice = function() {
		var id = $scope.selectedRoute.id;
		$http.get('/invoice/getAll/' + id).success(function(data) {
			$scope.invoiceList = data;
		})

	};
	$scope.makePayment = function() {
		var invoiceCode = $scope.selectedInvoice.invoiceCode;

		var invoiceAmount = $scope.selectedInvoice.totalPayableAmount;

		$http({
			method : "POST",
			url : "/collection/addPayment",
			data : {
				"invoiceCode" : invoiceCode,
				"amountCollected" : $scope.amountCollected,
				"invoiceAmount" : invoiceAmount
			},
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			$scope.paymentDone = data;
		})

	};

});