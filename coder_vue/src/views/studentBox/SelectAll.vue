<template>
  <div class="container">
    <div style="margin-top: 2%;margin-bottom: 1%">
     <!-- 查找框 -->
     <el-form :inline="true" :model="formInline" class="demo-form-inline" ref="find">
        <el-form-item label="ID" :rules="[
            { required: true, message: '请输入ID！', trigger: 'blur' }
        ]" prop="id">
            <el-input v-model="formInline.id" placeholder="请输入ID查询"></el-input>
        </el-form-item>

        <!-- <el-form-item label="学号" :rules="[{
            required: true, message: '请输入学号！', trigger: 'blur'
        }]" prop="number">
            <el-input v-model="formInline.number" placeholder="请输入学号查询"></el-input>
        </el-form-item> -->

        <el-form-item>
            <el-button type="button1" @click="serachName">查询</el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="button2" @click="getInfo()">查询重置</el-button>
        </el-form-item>

        <el-form-item>
            <el-button type="button3" @click="rising">按排名升序</el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="button4" @click="rankdown">按排名降序</el-button>
        </el-form-item>

      </el-form>
    </div>
    <el-table class="elTable" :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)" stripe border style="width: 100%;height: 500px;" v-loading="loading"
      :header-cell-style="{background:'#85CCB1', color:'#797979', borderColor:'#85CCB1'}"
      >
      @sort-change="sortChange"
      <el-table-column prop="id" label="id" width="130px" sortable ></el-table-column>
      <el-table-column prop="date" label="date" width="210" sortable ></el-table-column>
      <el-table-column prop="contest" label="contest" width="180px"></el-table-column>
      <el-table-column prop="rank" label="rank"></el-table-column>
      <el-table-column prop="performance" label="performance" width="120px"></el-table-column>
      <el-table-column prop="newRating" label="newRating" width="120px"></el-table-column>
      <el-table-column prop="diff" label="diff" width="80px"></el-table-column>
      <el-table-column prop="count" label="count"></el-table-column>
      <el-table-column prop="maxrating" label="maxrating"></el-table-column>
<!--      <el-table-column  label="操作" width="100px">
      <template slot-scope="scope">
        <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
        <el-button @click="handleDel(scope.row)" type="text" size="small">删除</el-button>
      </template>
      </el-table-column>-->
    </el-table>
    <div class="block">
      <el-pagination
        layout="prev, pager, next"
        :total="totalNum"
        :page-size="pageSize"
        :current-page="currentPage"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      pageSize: 5, // 表示一页多少条数据
      totalNum: 0,
      currentPage: 1,
      tableData: [],
      loading: true,
      formInline: {
          id: ''
      },
      error: ''
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.tableData = []// 清空原来的数据
      axios.get('/stu/info/acmer/atcoder/all/1/100').then(res => {
        this.loading = true; // 开始加载数据，显示 loading 状态
        this.error = null; // 清空错误信息
        if (res.data.code === 200) {
          this.loading = false
          const msgInfo = res.data.data.records
          for (const item in msgInfo) {
            this.tableData.push({
              id: msgInfo[item].acId,
              date: msgInfo[item].acDate,
              contest: msgInfo[item].acContest,
              rank: msgInfo[item].acRank,
              performance: msgInfo[item].acPerformance,
              newRating: msgInfo[item].acNewrating,
              diff: msgInfo[item].acDiff,
              count: msgInfo[item].acCount,
              maxrating: msgInfo[item].acMaxrating,
            })
          }
          this.totalNum = this.tableData.length
        }
      })
    },

    serachName() {
      // 发送查询请求
      this.tableData = []// 清空原来的数据
      axios.get(`/stu/info/acmer/atcoder/all/${this.formInline.id}`)
        .then(response => {
          this.loading = true; // 开始加载数据，显示 loading 状态
          this.error = null; // 清空错误信息
          // 处理查询结果
          if (response.data.code === 200) {
            this.loading = false
            //更新tableData
            const item = response.data.data
            console.log(response.data)
            console.log(item)
            this.tableData.push({
              id: item.acId,
              date: item.acDate,
              contest: item.acContest,
              rank: item.acRank,
              performance: item.acPerformance,
              newRating: item.acNewrating,
              diff: item.acDiff,
              count: item.acCount,
              maxrating: item.acMaxrating,
            })
            this.totalNum = this.tableData.length
          } else {
            console.log()
            this.error = response.data.message;
          }
        })
        .catch(error => {
          console.error(error);
          this.$message.error('查询失败');
        });
    },
    //升序排序
    rising () {
      //console.log(12121212)
      axios.get('/stu/info/acmer/atcoder//rank/asc')
        .then(response => {
          console.log(2323232)
          console.log(response)
          if (response.data.code === 200) {
            this.tableData=[]
            const msgInfo=response.data.data
            for(const item in msgInfo){
              this.tableData.push(
                {
                  id: msgInfo[item].acId,
                  date: msgInfo[item].acDate,
                  contest: msgInfo[item].acContest,
                  rank: msgInfo[item].acRank,
                  performance: msgInfo[item].acPerformance,
                  newRating: msgInfo[item].acNewrating,
                  diff: msgInfo[item].acDiff,
                  count: msgInfo[item].acCount,
                  maxrating: msgInfo[item].acMaxrating,
                }
              )
            }
            this.totalNum = this.tableData.length
          }
      })
      .catch(error => {
          console.error(error);
          this.$message.error('排序失败');
        });
    },

    //降序排序
    rankdown () {
      //console.log(12121212)
      axios.get('/stu/info/acmer/atcoder//rank/desc')
        .then(response => {
          console.log(2323232)
          console.log(response)
          if (response.data.code === 200) {
            this.tableData=[]
            const msgInfo=response.data.data
            for(const item in msgInfo){
              this.tableData.push(
                {
                  id: msgInfo[item].acId,
                  date: msgInfo[item].acDate,
                  contest: msgInfo[item].acContest,
                  rank: msgInfo[item].acRank,
                  performance: msgInfo[item].acPerformance,
                  newRating: msgInfo[item].acNewrating,
                  diff: msgInfo[item].acDiff,
                  count: msgInfo[item].acCount,
                  maxrating: msgInfo[item].acMaxrating,
                }
              )
            }
            this.totalNum = this.tableData.length
          }
      })
      .catch(error => {
          console.error(error);
          this.$message.error('排序失败');
        });
    },

    
    handleCurrentChange (val) {
      this.currentPage = val
    }
  }
}
</script>

<style scoped>
.container {
  margin-top: 20px;
  .demo-form-inline {
      text-align: left;
  }
  width: 100%;
  background-color: white;
  box-sizing: border-box;
  border-bottom: #eceef0 solid 2px;
  padding: 0px 18px;
}
.headBox {
  width: 100%;
  height: 75px;
  display: flex;
  align-items: center;
  box-sizing: border-box;
}
.headBox p {
  display: flex;
  justify-content: center;
  align-items: center;
}
.headBox p span {
  display: inline-block;
  width: 70px;
  margin-right: 5px;
  color: #7a7d7f;
  font-weight: bold;
}
.headBox p:nth-child(1) span,
.headBox p:nth-child(2) span {
  margin-right: -10px;
}
.headBox p:nth-child(2) span,
.headBox p:nth-child(3) span,
.headBox button {
  margin-left: 7px;
}
.headBox button span {
  margin-left: 5px;
}
.container .block {
  display: flex;
  justify-content: center;
  margin-top: 5px;
}
.elTable {
    border: 1px solid #85CCB1
  }
  .el-button--button1 {
    color: #AAD19F;
    background-color: white;
    border-color: #AAD19F;
  }
.el-button--button1:hover {
    color: white;
    background-color: #AAD19F;
    border-color: #AAD19F;
  }
  .el-button--button2 {
    color: #97C2AD;
    background-color: white;
    border-color: #97C2AD;
  }
  .el-button--button2:hover {
    color: white;
    background-color: #97C2AD;
    border-color: #97C2AD;
  }
  .el-button--button3 {
    color: #84B3BB;
    background-color: white;
    border-color: #84B3BB;
  }
  .el-button--button3:hover {
    color: white;
    background-color: #84B3BB;
    border-color: #84B3BB;
  }
  .el-button--button4 {
    color: #5EAD82;
    background-color: white;
    border-color: #5EAD82;
  }
  .el-button--button4:hover {
    color: white;
    background-color: #5EAD82;
    border-color: #5EAD82;
  }
</style>
