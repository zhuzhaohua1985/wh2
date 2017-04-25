<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<title><s:message code="sys.title" /></title>
		<meta charset="utf-8">
       	<meta name="viewport" content="width=device-width,initial-scale=1.0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="renderer" content="webkit">
		<link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
		<link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="${basePath}/css/dataTables/dataTables.bootstrap.css"/>
		<link rel="stylesheet" href="${basePath}/css/bootstrap-editable/bootstrap-editable.css"/>
		<link rel="stylesheet" href="${basePath}/css/bootstrap-dialog/bootstrap-dialog.min.css"/>
		<link rel="stylesheet" href="${basePath}/css/animate.min.css"/>
		<link rel="stylesheet" href="${basePath}/css/ztree/ztree.css"/>
		<link rel="stylesheet" href="${basePath}/css/style.min.css"/>
	</head>

	<body class="gray-bg">
	<div class="spiner-example">
       <div class="sk-spinner sk-spinner-three-bounce">
            <div class="sk-bounce1"></div>
            <div class="sk-bounce2"></div>
            <div class="sk-bounce3"></div>
        </div>
    </div>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                       	<a href="${basePath}/netbarList/list" class="btn btn-white btn-margin-right"><i class="fa fa-refresh"></i>&nbsp;刷新</a>
                       	<input id="zNodes" type="hidden" value='${areasTree}'>
                    </div>
                    <div class="ibox-content">
                    	<div class="row">
                    		<div class="col-sm-3">
                    			<ul id="nodeTree" class="ztree" style="padding-left: 20%;"></ul>
                    		</div>
                    		<div class="col-sm-9">
		                        <table class="table table-striped table-bordered table-hover " id="editable">
		                            <thead>
		                                <tr>
		                                    <th>市</th>
											<th>在线网吧</th>
											<th>离线网吧</th>
											<th>机器总数</th>
											<th>当前用户总数</th>
		                                </tr>
		                            </thead>
		                            <tbody></tbody>
		                        </table>
                    		</div>
                   		</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${basePath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${basePath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${basePath}/js/dataTables/jquery.dataTables.min.js"></script>
    <script src="${basePath}/js/dataTables/dataTables.bootstrap.min.js"></script>
    <script src="${basePath}/js/dataTables/dataTables.extends.js"></script>
    <script src="${basePath}/js/bootstrap-editable/bootstrap-editable.min.js"></script>
    <script src="${basePath}/js/dataTables/dataTables.editable.js"></script>
    <script src="${basePath}/js/bootstrap-dialog/bootstrap-dialog.min.js"></script>
    <script src="${basePath}/js/handlebars.js"></script>
    <script src="${basePath}/js/ztree/jquery.ztree.min.js"></script>
    <script src="${basePath}/js/content.min.js?v=1.0.0"></script>
    <script src="${basePath}/js/common.js" charset="utf-8"></script>
    <script>
    	$(document).ready(function(){
    		var url = '${basePath}/netbarList/edit';
    		var onClick = function(event, treeId, treeNode, clickFlag) {
    			dbTable.columns(3).search('^'+treeNode.id+'$', true, false).draw();//正则表达式搜索
    		}
    		var setting = {
   				view:{selectedMulti: false},
   				data:{simpleData:{enable:true, idKey:'id', pidKey:'pId', rootPId:0}},
   				callback: {onClick: onClick}
   			};
    		var treeObj = $.fn.zTree.init($('#nodeTree'), setting, parseJSON($('#zNodes').val()));
    		$('#zNodes').remove();
    		
    		initDtSearch();//表格搜索框回车查询
    		var columns = [{data:'qhdm'},{data:'zxwb'},{data:'lxwb'},{data:'jqzs'},{data:'yhzs'}];
    		//表格初始化
			oTable = $('#editable').dataTable({//dom:dtDom
				scrollY: $('body').height()-260,
				scrollX: true, fixedColumns:{leftColumns: 3},
				columns:columns,
	            columnDefs:columnDefs,autoWidth:false,
				order:[[0, 'desc']],
				processing: true, serverSide: true,//pipeline pages 管道式分页加载数据，减少ajax请求
			    ajax: {url:'${basePath}/netbarList/load', type:'POST'}, 
			    searchDelay: 300, deferRender: true,//当处理大数据时，延迟渲染数据，有效提高Datatables处理能力
			    drawCallback: function(settings){//Datatables每次重绘后执行
			    	//$('input[type="checkbox"].checkable').uniform();
	    			//initCheckbox();
	    			//$('.table th input.checkable').prop('checked', false).parent('span').removeClass('checked');
			    }
			});//返回JQuery对象，api()方法添加到jQuery对象,访问API.
			dbTable = oTable.api();//返回datatable的API实例,
	        $('#editable tbody').on('click', 'i.fa-save', function(){
				var $this = $(this);
				var tr = $this.parents('tr');
				var element = tr.find('a[data-type]');
				var obj = element.editable('getValue');
				if($.trim(obj.name)==''){
					BootstrapDialog.alert({type:'type-default', message:'部门名称不能为空！'});
					return;
				}
				var levelTd = tr.find('td:eq(2)');
				obj.level = levelTd.html();
				obj.parentId = levelTd.next().html();
				var data = JSON.parse(JSON.stringify(obj));
				$.com.ajax({
			       	url: '${basePath}/group/add', 
		           	data:data,
			       	success: function(data) {
			           	if(data.flag) {
			           		element.removeClass('editable-unsaved');
			           		element.editable('destroy');//销毁重新初始化
			           		var id = data.obj.id;
			           		tr.find('td:first').html(id);
			           		element.attr('data-pk', id);
			           		tr.find('a[data-type="text"],a[data-type="number"]').editable({
			           			pk:id, disabled:true, url:url
			    			}).on('save', adjustColumn);
				        	tr.find('a[id="status"]').editable({pk:id, disabled:true, source:state, url:url});
				        	checkVal(tr);//验证数据
			               	$this.removeClass('fa-save').addClass('fa-edit');
			               	tr.find('i.fa-remove').remove();
			               	element.off('save');//解绑自动显示下一列编辑框事件
			               	dbTable.columns.adjust();//重新计算列宽
			           	}else{
			           		BootstrapDialog.alert({type:'type-danger', message:'保存失败，请刷新重试！'});
			           	}               
		       		}
				});
			}).on('click', 'i.fa-edit', function(){//编辑
				$(this).parents('tr').find('a[data-type]').editable('toggleDisabled');
			});
	        $('.spiner-example').remove();//移除遮罩层
		});
	</script>
</body>
</html>
