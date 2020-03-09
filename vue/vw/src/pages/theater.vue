<template>
	<div id="app">
		<div class = "title">
			<el-row :gutter="20">
				<el-col>
					<div>
						<el-menu :default-active="activeIndex1" 
							class="el-demo1" 
							mode="horizontal" 
							@select="handleSelect" 
							text-color="#000" 
							active-text-color="#eb002a" 
							router 
							style="width: 500px;margin-left:auto; margin-right:auto;">
								<el-menu-item index="/" style="font-size: 18px;">首页</el-menu-item>
								<el-menu-item index="film" style="font-size: 18px;">影片</el-menu-item>
								<el-menu-item index="theater" style="font-size: 18px;">影院</el-menu-item>
						</el-menu>
					</div>
				</el-col>
			</el-row>			
		</div>
		<div>
			<el-container style="width: 1000px;margin-left:auto; margin-right:auto;margin-bottom:20px;margin-top: 20px;">
				<el-breadcrumb separator-class="el-icon-arrow-right"  style="font-size: 16px;">
				    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				    <el-breadcrumb-item :to="{ path: '/theater' }">影院</el-breadcrumb-item>
				</el-breadcrumb>
			</el-container>
		</div>
		<div>
			<el-container style="width: 1000px;margin-left:auto; margin-right:auto;">
				<el-container style="width: 600px;margin-left:auto; margin-right:auto;">
					<div style="width: 600px;margin-left:auto; margin-right:auto;">
							<el-row>
							    <el-col style="width: 150px;margin-left:auto; margin-right:auto;">
							    	<div style="background-color:#ffcdd2;">
								    	<div style="text-align:left;font-size: 14px;margin-left:40px;line-height:3;color:#797979;padding: 10px;">
											选择地区
								    	</div>
										<div style="text-align:left;font-size: 14px;margin-left:60px;line-height:3;color:#797979;">
											搜索
										</div>
									</div>	
							    </el-col>
								<el-col style="width: 250px;background-color:#ffebee;">
							    	<div>
							    		<div >
							    			<el-select v-model="value"  placeholder="请选择地区" clearable style="margin-left:50px;margin-top: 15px;">
											    <el-option-group
											      v-for="group in options"
											      :key="group.label"
											      :label="group.label">
											      <el-option
											        v-for="item in group.options"
											        :key="item.value"
											        :label="item.label"
											        :value="item.value">
											      </el-option>
											    </el-option-group>
											</el-select>
							    		</div>
							    		<div>
							    			<el-input
											    placeholder="请输入影院名称/关键字"
											    v-model="input"
											    style="margin-left:50px;width: 250px;margin-bottom: 9px;margin-top: 20px;">
											</el-input>
											
							    		</div>
							    	</div>
							    </el-col>
							     <el-col style="width: 200px;padding-top: 65px;padding-bottom: 9px;background-color:#ffebee;">	
							    	<div>
										<el-button type="danger" size="small" icon="el-icon-search"  @click="search">搜索
										</el-button>
									</div>
								</el-col>
							</el-row>
							<el-row style="width: 600px;margin-left:auto; margin-right:auto;border-top: 2px solid #d50000 ;border-left: 1px solid #eee;border-right: 1px solid #eee;">
								<el-col>
									<div style="margin: 20px;">
										排序:
										<el-button plain>综合</el-button>
										<el-button plain>评分</el-button>
										当前条件下共有<span style="color: #d50000;">{{num}}</span>家
									</div>
								</el-col>
							</el-row>
							<el-row style="width: 600px;margin-left:auto; margin-right:auto;">
								<el-col>
									<div >
									    <el-table
										    :data="tableData"
										    stripe
										    style="width: 100%">
										    <el-table-column
										        prop="zone"
										        label="地区"
										       	width="100">
										    </el-table-column>
										    <el-table-column
										        prop="theater_name"
										        label="影院名称"
										        width="250">
										    </el-table-column>
										    <el-table-column
										       	prop="telno"
										       	label="电话">
										    </el-table-column>
										    <el-table-column
										        fixed="right"
										        label=""
										        width="100">
										        <template slot-scope="scope">
											        <el-button slot="reference" @click="hand">购票</el-button>
											        <el-dialog title="请选择您要购买的电影票数" :visible.sync="dialogTableVisible" right :append-to-body='true' :lock-scroll="false" width="300px"> 
											        <ticketNum @child="childData"></ticketNum>
											        </el-dialog>
										        </template>
										    </el-table-column>
									    </el-table>
									</div>
								</el-col>
							</el-row>
					</div>
				</el-container>
				<el-container style="width: 400px;height:400px;border: 1px solid #eee;background-color:#fafafa;">
					<div>
						<div style="text-align:left;font-size: 18px;margin-left:30px;margin-top: 30px;">
							热销团购券
						</div>
						<div style="width: 340px;height:300px;margin:10px 30px;border-top: 2px solid #eee;background-color:#f5f5f5">
							<div style="width: 340px;padding-left: 130px;padding-right: 150px;margin-top: 50px;">
								<img src="../assets/icon/1.png" class="image">
							</div>
							<div style="text-align:left;font-size: 20px;margin-left:100px;margin-top: 10px;">
								暂无团购信息
							</div>
							<div style="text-align:left;font-size: 14px;margin-left:30px;margin-top: 10px;">
								你可以查看<el-button type="text" style="color:#d50000">全部影院</el-button>或者<el-button type="text" style="color:#d50000">全部影片</el-button>
							</div>
						</div>
					</div>
				</el-container>
			</el-container>
		</div>
	</div>	
</template>

<script>
import ticketNum from '../components/ticketNum'
	export default {
		  components: {
            ticketNum
        },
		data() {
	        return {
			       	activeIndex1: 'theater',
			       	input: '',
					num: '  ',
					num1: '1',
				    visible: false,
				    dialogTableVisible: false,
		            options: [{
				          	label: '热门城市',
				       		options: [{
								        value: '12',
							            label: '上海'
						        	}, {
							            value: '10',
							            label: '北京'
						        	}]
			        		}, {
			          		label: '城市名',
					        options: [{
							            value: '14',
							            label: '成都'
						          	}, {
							            value: '16',
							            label: '深圳'
							        }, {
							            value: '11',
							            label: '广州'
							        }, {
							            value: '15',
							            label: '大连'
						            }, {
							            value: '13',
							            label: '重庆'
						            }, {
							            value: '17',
							            label: '杭州'
						            }, {
							            value: '18',
							            label: '拉萨'
						            }]
					        }],
					value: '',
					tableData: [],      


			        }
			    },
	  
	    methods:{
	        handleSelect(key, keyPath) {
	        	console.log(key, keyPath);
	        },
	        search(){
	        	this.$ajax('/api/cinema',{
	        		params:{
	        			zoneId :this.value,
	        			keyword:this.input,
	        		}
	        	}).then(res => {
			          this.tableData = res.data;
			          this.num = this.tableData.length;
			          console.log(res.data);
		        }).catch(function (error) {
		          console.log(error);
		        });
		    },
	       hand(){
	       		 this.dialogTableVisible=true;
	       },
	       childData(data){
	       		this.dialogTableVisible=data;
	       },
    	},
    	
    	mounted() {
	        
	       
        }
	}	

</script>

<style>
#app{
    top: 0px;
    background-color: #ffffff;
    height: 100%;
}
.title{
	margin: 15px;
}
.el-input__inner{
	background-color:#fff;
	border:1px solid #DCDFE6;
	color:#606266;
	height:30px;
	width: 200px;
	border-radius:0;
	}
.el-select .el-input__inner:focus {
	border-color:#d50000;
} 
.el-select .el-input__inner:focus.el-input__inner{
	border-color:#d50000;
}
.el-select-dropdown__item.selected{
	color:#d50000;
    font-weight:700;
} 
.el-select .el-input.is-focus .el-input__inner{
	border-color:#d50000;
} 
.el-input.is-active .el-input__inner, .el-input__inner:focus{
	border-color:#d50000;
	outline:0;
}
.el-button--danger:focus, .el-button--danger:hover{
	background:#FFF;
	border-color:#d50000;
	color:#fff;
}
.el-button--small, .el-button--small.is-round{
	padding:8px 12px 8px 12px;
} 
.el-button--mini, .el-button--small{
	font-size:12px;
	border-radius:0;
} 
.el-button.is-plain:focus, .el-button.is-plain:hover{
	background:#FFF;
	border-color:#d50000;
	color:#d50000;
}  
.el-button{
	padding:8px 12px;
	border-radius: 0;
}
.el-button--text:focus, .el-button--text:hover{
	color:#d50000;
}
.el-button:focus, .el-button:hover{
	color:#d32f2f;
	border-color:#ffcdd2;
	background-color:#ffebee;
} 
</style>