(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-76cac56b"],{"34d9":function(t,e,r){},4731:function(t,e,r){"use strict";r.d(e,"e",(function(){return a})),r.d(e,"f",(function(){return i})),r.d(e,"d",(function(){return s})),r.d(e,"h",(function(){return n})),r.d(e,"a",(function(){return l})),r.d(e,"b",(function(){return d}));var o=r("b775");function a(t){return Object(o["a"])({url:"/port/getPage",method:"post",data:t})}function i(t){return Object(o["a"])({url:"/port/getPortList",method:"post",data:t})}function s(t){return Object(o["a"])({url:"/port/delete",method:"get",params:t})}function n(t){return Object(o["a"])({url:"/port/save",method:"post",data:t})}function l(t){return Object(o["a"])({url:"/port/batchDelete",method:"post",data:t})}function d(t){return Object(o["a"])({url:"/port/batchSave",method:"post",data:t})}},"58b2":function(t,e,r){"use strict";r.r(e);var o=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"app-container"},[r("div",{staticClass:"searchBody"},[r("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:t.showAddDialog}},[t._v("添加服务器")]),r("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-refresh"},on:{click:t.refreshServerList}},[t._v("刷新")])],1),r("div",{staticClass:"item-container",attrs:{id:"itemBox"}},t._l(t.tableData,(function(e,o){return r("div",{key:o,staticClass:"item-box"},[r("div",{staticClass:"server-status"},t._l(t.stateList,(function(o,a){return e.state===o.state?r("span",{key:e+a,class:t.computeClass(e.state)},[r("i",{directives:[{name:"show",rawName:"v-show",value:2===e.state,expression:"item.state === 2"}],staticClass:"el-icon-warning"}),r("i",{directives:[{name:"show",rawName:"v-show",value:1===e.state,expression:"item.state === 1"}],staticClass:"el-icon-loading"}),r("i",{directives:[{name:"show",rawName:"v-show",value:3===e.state,expression:"item.state === 3"}],staticClass:"el-icon-success"}),t._v(" "+t._s(o.stateName)+" ")]):t._e()})),0),r("div",{staticClass:"box-col",staticStyle:{"font-size":"18px","font-weight":"bold"}},[t._v(t._s(e.serverName))]),r("div",{staticClass:"box-col"},[r("label",[t._v("服务器地址:")]),t._v(t._s(e.host))]),r("div",{staticClass:"box-col"},[r("label",[t._v("SSH端口:")]),t._v(t._s(e.port)+" ")]),r("div",{staticClass:"box-col"},[r("label",[t._v("用户名:")]),t._v(t._s(e.username))]),r("div",{staticClass:"box-col"},[r("label",[t._v("检测时间:")]),t._v(t._s(t._f("parseTime")(e.updateTime||e.createTime,"{y}-{m}-{d} {h}:{i}:{s}")))]),r("div",{staticClass:"box-trl"},[r("el-button",{attrs:{type:"text",icon:"el-icon-edit",size:"mini",title:"编辑"},on:{click:function(r){return t.showEditDialog(e)}}},[t._v("编辑")]),r("el-button",{attrs:{type:"text",size:"mini",icon:"el-icon-circle-plus"},on:{click:function(r){return t.showPortListDialog(e)}}},[t._v("端口维护")]),r("el-button",{attrs:{type:"text",icon:"el-icon-delete",size:"mini",title:"删除"},on:{click:function(r){return t.deleteData(e)}}},[t._v("删除")])],1)])})),0),t.dataTotal/t.searchForm.pageSize>1?r("xd-pager",{attrs:{fixed:"",background:"","page-sizes":[8,16,32],"page-size":t.searchForm.pageSize,"current-page":t.searchForm.pageNum,layout:"total, sizes, prev, pager, next, jumper",total:t.dataTotal},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}):t._e(),r("el-drawer",{attrs:{title:"端口管理","with-header":!1,visible:t.portListDialog,direction:"rtl",size:t.drawerPercent?t.drawerPercent:"30%"},on:{"update:visible":function(e){t.portListDialog=e}}},[r("div",{staticClass:"drawer-body",attrs:{id:"draw"}},[r("el-button",{attrs:{size:"mini",type:"primary",icon:"el-icon-plus"},on:{click:t.showAddPortDialog}},[t._v("添加端口")]),r("el-button",{attrs:{size:"mini"},on:{click:t.showDeleteDialog}},[t._v("删除端口段")]),r("el-button",{attrs:{size:"mini"},on:{click:t.refreshPortList}},[t._v("刷新")]),r("el-button",{attrs:{size:"mini"},on:{click:function(e){t.portListDialog=!1}}},[t._v("取 消")]),r("div",{staticStyle:{"font-size":"12px",color:"#606266",margin:"10px 0 5px 0"}},[r("i",{staticClass:"el-icon-warning",staticStyle:{"margin-right":"3px"}}),t._v("端口为异步添加, 请点击刷新查询端口是否添加完成")]),r("el-table",{attrs:{data:t.portList}},[r("el-table-column",{attrs:{property:"localPort",label:"本地端口"}}),r("el-table-column",{attrs:{property:"internetPort",label:"外网端口"}}),t.isMobile?t._e():r("el-table-column",{attrs:{label:"创建时间",width:"160"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("span",[t._v(t._s(t._f("parseTime")(e.row.createTime,"{y}-{m}-{d} {h}:{i}:{s}")))])]}}],null,!1,1833597504)}),r("el-table-column",{attrs:{label:"操作",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-row",{staticStyle:{margin:"5px"}},[r("el-button",{attrs:{type:"text",size:"mini",title:"编辑"},on:{click:function(r){return t.showEditPortDialog(e.row)}}},[t._v("编辑")])],1),r("el-row",{staticStyle:{margin:"5px"}},[r("el-button",{attrs:{type:"text",size:"mini",title:"删除"},on:{click:function(r){return t.deletePort(e.row)}}},[t._v("删除")])],1)]}}])})],1),r("div",{staticClass:"block"},[r("el-pagination",{attrs:{background:"","page-sizes":[10,20,50],"page-size":t.portSearchForm.pageSize,"current-page":t.portSearchForm.pageNum,layout:"total,  prev, next, jumper",total:t.portDataTotal},on:{"size-change":t.handlePortSizeChange,"current-change":t.handlePortCurrentChange}})],1)],1)]),r("el-dialog",{attrs:{title:"添加服务器",visible:t.addDialog,width:"30%"},on:{"update:visible":function(e){t.addDialog=e}}},[r("el-form",{ref:"addForm",attrs:{model:t.addForm,rules:t.addFormRules,"label-width":"80px",size:"small"}},[r("el-form-item",{attrs:{label:"名称",prop:"serverName"}},[r("el-input",{attrs:{size:"mini"},model:{value:t.addForm.serverName,callback:function(e){t.$set(t.addForm,"serverName",e)},expression:"addForm.serverName"}})],1),r("el-form-item",{attrs:{label:"地址",prop:"host"}},[r("el-input",{attrs:{size:"mini"},model:{value:t.addForm.host,callback:function(e){t.$set(t.addForm,"host",e)},expression:"addForm.host"}})],1),r("el-form-item",{attrs:{label:"端口",prop:"port",rules:[{required:!0,message:"端口不能为空"},{type:"number",message:"端口必须为数字值"}]}},[r("el-input",{attrs:{size:"mini"},model:{value:t.addForm.port,callback:function(e){t.$set(t.addForm,"port",t._n(e))},expression:"addForm.port"}})],1),r("el-form-item",{attrs:{label:"用户名",prop:"username"}},[r("el-input",{attrs:{size:"mini",placeholder:"root用户"},model:{value:t.addForm.username,callback:function(e){t.$set(t.addForm,"username",e)},expression:"addForm.username"}})],1),r("el-form-item",{attrs:{label:"密码",prop:"password"}},[r("el-input",{attrs:{size:"mini"},model:{value:t.addForm.password,callback:function(e){t.$set(t.addForm,"password",e)},expression:"addForm.password"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.addDialog=!1}}},[t._v("取 消")]),r("el-button",{attrs:{type:"primary"},on:{click:t.confirmAddForm}},[t._v("确 定")])],1)],1),r("el-dialog",{attrs:{title:"添加端口",visible:t.addPortDialog,width:"30%"},on:{"update:visible":function(e){t.addPortDialog=e}}},[r("el-form",{ref:"addPortForm",attrs:{model:t.addPortForm,rules:t.addPortFormRules,"label-width":"100px",size:"small"}},[r("el-form-item",{attrs:{label:"本地端口",prop:"localPort",rules:[{required:!0,message:"端口不能为空"}]}},[r("el-input",{attrs:{size:"mini",placeholder:"单端口如:80,多端口80-83"},model:{value:t.addPortForm.localPort,callback:function(e){t.$set(t.addPortForm,"localPort",e)},expression:"addPortForm.localPort"}})],1),r("el-form-item",{attrs:{label:"外网端口",prop:"internetPort"}},[r("el-input",{attrs:{size:"mini",placeholder:"不填默认和本地端口相同"},model:{value:t.addPortForm.internetPort,callback:function(e){t.$set(t.addPortForm,"internetPort",e)},expression:"addPortForm.internetPort"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.addPortDialog=!1}}},[t._v("取 消")]),r("el-button",{attrs:{type:"primary"},on:{click:t.confirmAddPort}},[t._v("确 定")])],1)],1),r("el-dialog",{attrs:{title:"删除端口",visible:t.deletePortRangeDialog,width:"30%"},on:{"update:visible":function(e){t.deletePortRangeDialog=e}}},[r("el-form",{ref:"deletePortForm",attrs:{model:t.deletePortForm,rules:t.addPortFormRules,"label-width":"100px",size:"small"}},[r("el-form-item",{attrs:{label:"本地端口",prop:"localPort",rules:[{required:!0,message:"端口不能为空"}]}},[r("el-input",{attrs:{size:"mini",placeholder:"单端口如:80,多端口80-83"},model:{value:t.deletePortForm.localPort,callback:function(e){t.$set(t.deletePortForm,"localPort",e)},expression:"deletePortForm.localPort"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.deletePortRangeDialog=!1}}},[t._v("取 消")]),r("el-button",{attrs:{type:"primary"},on:{click:t.confirmDeletePort}},[t._v("确 定")])],1)],1),r("el-dialog",{attrs:{title:"修改端口",visible:t.editPortDialog,width:"30%"},on:{"update:visible":function(e){t.editPortDialog=e}}},[r("el-form",{ref:"editPortForm",attrs:{model:t.editPortForm,rules:t.addPortFormRules,"label-width":"120px",size:"small"}},[r("el-form-item",{attrs:{label:"本地端口",prop:"localPort",rules:[{required:!0,message:"端口不能为空"},{type:"number",message:"端口必须为数字值"}]}},[r("el-input",{attrs:{size:"mini"},model:{value:t.editPortForm.localPort,callback:function(e){t.$set(t.editPortForm,"localPort",t._n(e))},expression:"editPortForm.localPort"}})],1),r("el-form-item",{attrs:{label:"外网端口",prop:"internetPort",rules:[{required:!0,message:"端口不能为空"},{type:"number",message:"端口必须为数字值"}]}},[r("el-input",{attrs:{size:"mini"},model:{value:t.editPortForm.internetPort,callback:function(e){t.$set(t.editPortForm,"internetPort",t._n(e))},expression:"editPortForm.internetPort"}})],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.editPortDialog=!1}}},[t._v("取 消")]),r("el-button",{attrs:{type:"primary"},on:{click:t.confirmEditPort}},[t._v("确 定")])],1)],1)],1)},a=[],i=r("aa22"),s=r("4731"),n={computed:{},data:function(){return{tableData:[],dataTotal:null,portDataTotal:null,searchForm:{pageSize:8,pageNum:1},portSearchForm:{pageSize:8,pageNum:1,serverId:null},addForm:{id:null,serverName:null,addType:"add",password:null,username:"root",port:22},addPortForm:{id:null,username:null,password:null},deletePortForm:{id:null,username:null,password:null,localPort:"0-65535"},addFormRules:{serverName:[{required:!0,trigger:"blur",message:"必需项"}],host:[{required:!0,trigger:"blur",message:"必需项"}],username:[{required:!0,trigger:"blur",message:"必需项"}]},addPortFormRules:{},addDialog:!1,selectedRow:null,portList:[],portListDialog:!1,addPortDialog:!1,deletePortRangeDialog:!1,editPortDialog:!1,editPortForm:{},stateList:[{state:1,stateName:"检测中"},{state:2,stateName:"连接失败"},{state:3,stateName:"在线"}],intervalId:null}},mounted:function(){var t=this;t.getData(),this.intervalId=setInterval((function(){t.getData()}),3e3)},beforeDestroy:function(){clearInterval(this.intervalId)},methods:{computeClass:function(t){if(t){if(1===t)return"state-normal";if(2===t)return"state-fail";if(3===t)return"state-online"}return"state-normal"},getData:function(){var t=this;Object(i["d"])(this.searchForm).then((function(e){t.tableData=e.data.list,t.dataTotal=e.data.total}))},refreshServerList:function(){var t=this;Object(i["d"])(this.searchForm).then((function(e){t.$notify({message:"刷新成功",type:"success"}),t.tableData=e.data.list,t.dataTotal=e.data.total}))},deleteData:function(t){var e=this;this.$confirm("确认删除?",{confirmButtonText:"确认",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["a"])({id:t.id}).then((function(t){e.$notify({message:"删除成功",type:"success"}),e.getData()}))}))},confirmAddForm:function(){var t=this;this.$refs["addForm"].validate((function(e){if(e){if("add"===t.addForm.addType&&!t.addForm.password)return void t.$notify({message:"密码不能为空",type:"warning"});Object(i["e"])(t.addForm).then((function(e){t.$notify({message:"保存成功",type:"success"}),t.addDialog=!1,t.getData()}))}}))},handleSizeChange:function(t){this.searchForm.pageSize=t,this.getData()},handleCurrentChange:function(t){this.searchForm.pageNum=t,this.getData()},handlePortSizeChange:function(t){var e=this;this.portSearchForm.pageSize=t,Object(s["f"])(this.portSearchForm).then((function(t){e.portList=t.data.list,e.portDataTotal=t.data.total}))},handlePortCurrentChange:function(t){var e=this;this.portSearchForm.pageNum=t,Object(s["f"])(this.portSearchForm).then((function(t){e.portList=t.data.list,e.portDataTotal=t.data.total}))},refreshPortList:function(){var t=this;Object(s["f"])(this.portSearchForm).then((function(e){t.portList=e.data.list,t.portDataTotal=e.data.total}))},showAddDialog:function(){this.addDialog=!0,this.addForm.addType="add",this.addForm.id=null},showDeleteDialog:function(){this.deletePortRangeDialog=!0},showEditDialog:function(t){this.addDialog=!0,this.addForm=Object.assign(t),this.addForm.password=null,this.addForm.addType="edit"},showPortListDialog:function(t){var e=this;this.portListDialog=!0,this.portSearchForm.serverId=t.id,Object(s["f"])(this.portSearchForm).then((function(t){e.portList=t.data.list,e.portDataTotal=t.data.total}))},deletePort:function(t){var e=this;this.$confirm("确认删除?",{confirmButtonText:"确认",cancelButtonText:"取消",type:"warning"}).then((function(){Object(s["d"])({id:t.id}).then((function(t){e.$notify({message:"删除成功",type:"success"}),Object(s["f"])(e.portSearchForm).then((function(t){e.portList=t.data.list,e.portDataTotal=t.data.total}))}))}))},showAddPortDialog:function(){this.addPortDialog=!0},confirmAddPort:function(){var t=this;this.$refs["addPortForm"].validate((function(e){e&&(t.addPortForm.serverId=t.portSearchForm.serverId,Object(s["b"])(t.addPortForm).then((function(e){t.$notify({message:"请求添加成功,请点击刷新按钮查看添加进度",type:"success"}),t.addPortDialog=!1,Object(s["f"])(t.portSearchForm).then((function(e){t.portList=e.data.list,t.portDataTotal=e.data.total}))})))}))},confirmDeletePort:function(){var t=this;this.$refs["deletePortForm"].validate((function(e){e&&(t.deletePortForm.serverId=t.portSearchForm.serverId,Object(s["a"])(t.deletePortForm).then((function(e){t.$notify({message:"请求删除成功,请点击刷新按钮查看删除进度",type:"success"}),Object(s["f"])(t.portSearchForm).then((function(e){t.portList=e.data.list,t.portDataTotal=e.data.total}))})))}))},showEditPortDialog:function(t){this.editPortDialog=!0,this.editPortForm=Object.assign(t)},confirmEditPort:function(){var t=this;this.$refs["editPortForm"].validate((function(e){e&&Object(s["h"])(t.editPortForm).then((function(e){t.$notify({message:"保存成功",type:"success"}),t.editPortDialog=!1,Object(s["f"])(t.portSearchForm).then((function(e){t.portList=e.data.list,t.portDataTotal=e.data.total}))}))}))}}},l=n,d=(r("79dd"),r("2877")),c=Object(d["a"])(l,o,a,!1,null,"7fb10969",null);e["default"]=c.exports},"79dd":function(t,e,r){"use strict";var o=r("34d9"),a=r.n(o);a.a},aa22:function(t,e,r){"use strict";r.d(e,"d",(function(){return a})),r.d(e,"c",(function(){return i})),r.d(e,"b",(function(){return s})),r.d(e,"a",(function(){return n})),r.d(e,"e",(function(){return l}));var o=r("b775");function a(t){return Object(o["a"])({url:"/server/getPage",method:"post",data:t})}function i(t){return Object(o["a"])({url:"/server/getList",method:"get",data:t})}function s(t){return Object(o["a"])({url:"/server/getForwardServerList",method:"get",params:t})}function n(t){return Object(o["a"])({url:"/server/delete",method:"get",params:t})}function l(t){return Object(o["a"])({url:"/server/save",method:"post",data:t})}}}]);