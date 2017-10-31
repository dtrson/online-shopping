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
	//create a dataset
	
	
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
	};
});