$(function(){

	//solving the active menu problem
	switch(menu) {
		case 'Home': 
			$('#home').addClass('active'); 
			break;
		case 'About Us':
			$('#about').addClass('active');
			break;
		case 'Contact Us':
			$('#contact').addClass('active');
			break;
		case 'All Products':
			$('#listProducts').addClass('active');
			break;	
		case 'Manage Products':
			$('#manageProducts').addClass('active');
			break;	
		default:
			$('#listProducts').addClass('active');
			$('#a_'+menu).addClass('active');
			break;
	}
	
	//code for jquery dataTable
	var $table = $('#productListTable');
	
	//execute the below code only where we have this table
	if($table.length) {
		
		var jsonUrl = '';
		
		if(window.categoryId == ''){
			jsonUrl = window.contextRoot + '/json/data/all/products';
		}
		else{
			jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
		}
		
		$table.DataTable({
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'code',
					bSortable: false,
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"></img>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender: function(data,type,row){
						return '&#36; '+ data;
					}
				},
				{
					data: 'quantity',
					mRender: function(data,type,row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'id',
					bSortable: false,
					mRender: function(data,type,row){
						var str = '';
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><i class="fa fa-picture-o" aria-hidden="true"></i></a> &#160;';
						
						if(row.quantity <1){
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
						}else{
							str += '<a href="'+window.contextRoot+'/cart/add'+data+'/product" class="btn btn-success"><i class="fa fa-cart-plus" aria-hidden="true"></i></a>';
						}

						return str;
					}
				}
			]
		});
	}
	
	//dismissing the alert after 3 seconds
	var $alert = $('.alert')
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	

	
	
	//dataTable for Admin
	//code for jquery dataTable
	var $adminProductsTable = $('#adminProductsTable');
	
	//execute the below code only where we have this table
	if($adminProductsTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable.DataTable({
			ajax: {
				url: jsonUrl,
				dataSrc: ''
			},
			columns: [
				{
					data: 'id'
				},
				{
					data: 'code',
					bSortable: false,
					mRender: function(data,type,row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg"></img>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'quantity',
					mRender: function(data,type,row){
						if(data < 1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
					}
				},
				{
					data: 'unitPrice',
					mRender: function(data,type,row){
						return '&#36; '+ data;
					}
				},
				{
					data: 'active',
					bSortable: false,
					mRender: function(data,type,row){
						
						var str ='';
						str += '<label class="switch">';
						
						if(data){
							str +=		'<input type="checkbox" checked="checked" value="'+ row.id +'"/>';
						}
						else{
							str +=		'<input type="checkbox" value="'+ row.id +'"/>';
						}
						
						str +=		'<div class="slider"></div></label>';
						
						return str;
					}
				},
				{
					data:'id',
					bSortable: false,
					mRender: function(data,type,row){
						
						var str = '';
						str+= '<a href="'+ window.contextRoot +'/manage/'+ data +'/product" class="btn btn-warning"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>'
						return str;
					}
				}
			],
			initComplete:function(){
				
				//logic for the switch in admin product page
				var api = this.api();
				
				api.$('.switch input[type="checkbox"]').on('change', function(){ 	
					var checkbox = $(this);
					var checked = checkbox.prop('checked');
					var dMsg = (checked)? 'You want to activate the product?': 'You want to deactivate the product';
					var value = checkbox.prop('value');
				
					bootbox.confirm({
						title: 'Product Activation and Deactivation',
						message: dMsg,
					    buttons: {
					        confirm: {
					            label: 'Yes',
					            className: 'btn-success'
					        },
					        cancel: {
					            label: 'No',
					            className: 'btn-danger'
					        }
					    },
					    callback: function (confirmed) {
					    	if(confirmed){
								
					    		var activationUrl = window.contextRoot + '/manage/product/'+ value + '/activation';
					    		
					    		$.post(activationUrl, function(data){
					    			bootbox.alert({
										title:'Information',
										message: data
									});
					    		});
								
							}else{
								checkbox.prop('checked', !checked);
							}
					    }
					});
					
				});
			}
		});
	}
});