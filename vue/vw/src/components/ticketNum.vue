<template>
<div>
	
	<div style="margin-left: 50px;margin-right: auto;">
		<el-input-number size="mini" v-model="num1"></el-input-number>
	</div>
	<el-row style="margin-top: 20px;">
		<el-button plain @click="cancel" style="margin-left: 30px;">取消</el-button>
		<el-button type="primary" plain @click="confirm" style="margin-left: 60px;">确定</el-button>
	</el-row>
</div>
</template>

<script>
export default{
	name:"ticketNum",
	data(){
		return{
			num1:1,
			dialogTableVisible:false,
		}
	},
	
	methods:{
		cancel(){
			this.$emit("child", this.dialogTableVisible);
		},
		confirm(){
			this.$message({
		        message: '恭喜你，订票成功！',
		        type: 'success'
	        });
			this.$emit("child", this.dialogTableVisible);
		},
	}
}
</script>

<style>
.el-input__inner{
	width: 130px;
}

</style>