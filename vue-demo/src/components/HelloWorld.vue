<template>
  <div class="out-layer">
    <!-- 按钮 -->
    <div type="flex" class="right" justify="end">
      <Input v-model="searchStr" placeholder="请输入编号查询" style="width: 800px"/>
      <Button
        type="info"
        class="button"
        style="margin-right: 5px;margin-left: 5px"
        @click="search()"
      >查询</Button>
      <Button type="info" @click="AddBtnShow()">新增</Button>
    </div>
    <!-- 列表 -->
    <div>
      <Table border :columns="columns1" :data="dataList">
        <template slot-scope="{row}" slot="action">
          <Button type="primary" size="small" style="margin-right: 5px" @click="EditBtnShow(row)">修改</Button>
          <Button type="error" size="small" @click="UserDel(row)">删除</Button>
        </template>
      </Table>
    </div>

    <!-- 增加用户 -->
    <div>
      <Modal v-model="isAddShow" draggable footer-hide scrollable title="增加用户">
        <Form ref="addFormList" :model="addFormList" :rules="ruleValidate" :label-width="80">
          <FormItem label="编号" prop="no">
            <Input v-model="addFormList.no" placeholder="请输入你的编号" number/>
          </FormItem>
          <FormItem label="姓名" prop="name">
            <Input v-model="addFormList.name" placeholder="请输入你的姓名"/>
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <Input v-model="addFormList.email" placeholder="请输入你的邮箱"/>
          </FormItem>
          <FormItem>
            <Button type="primary" @click="HandleSubmit('addFormList')">提交</Button>
            <Button @click="HandleReset('addFormList')" style="margin-left: 8px">重置</Button>
          </FormItem>
        </Form>
      </Modal>
    </div>

    <!-- 修改用户 -->
    <div>
      <Modal v-model="isEditShow" draggable footer-hide scrollable title="修改用户">
        <Form ref="editFormList" :model="editFormList" :rules="ruleValidate" :label-width="80">
          <FormItem label="工号" prop="no">
            <Input disabled v-model="editFormList.no" placeholder="请输入你的编号"/>
          </FormItem>
          <FormItem label="姓名" prop="name">
            <Input v-model="editFormList.name" placeholder="请输入你的姓名"/>
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <Input v-model="editFormList.email" placeholder="请输入你的邮箱"/>
          </FormItem>
          <FormItem>
            <Button type="primary" @click="HandleSubmit('editFormList')">提交</Button>
            <Button @click="HandleReset('editFormList')" style="margin-left: 8px">重置</Button>
          </FormItem>
        </Form>
      </Modal>
    </div>
  </div>
</template>
<script>
import {
  UserQryAction,
  UserAddAction,
  UserDelAction,
  UserUpdateAction
} from "../api/user.js";
export default {
  data() {
    return {
      searchStr: "",
      isAddShow: false,
      isEditShow: false,
      columns1: [
        {
          title: "编号",
          key: "no"
        },
        {
          title: "名称",
          key: "name"
        },
        {
          title: "邮箱",
          key: "email"
        },
        {
          title: "操作",
          slot: "action",
          width: 150,
          align: "center"
        }
      ],
      dataList: [],
      data: [],
      addFormList: {
        no: "",
        name: "",
        email: ""
      },
      editFormList: {
        no: 0,
        name: "",
        email: ""
      },
      ruleValidate: {
        no: [
          {
            required: true,
            type: "number",
            message: "编号只能为数字！",
            trigger: "blur"
          }
        ],
        name: [
          {
            required: true,
            message: "姓名不能为空！",
            trigger: "blur"
          }
        ],
        email: [
          {
            required: true,
            message: "邮箱不能为空！",
            trigger: "blur"
          },
          {
            type: "email",
            message: "不合法的邮箱！",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    search() {
      if (this.searchStr.trim() !== "") {
        this.data.forEach(element => {
          if (element.no == this.searchStr) {
            var index = this.data.indexOf(element);
            this.dataList = [];
            this.dataList.push(this.data[index]);
          }
        });
      } else {
        this.dataList = this.data;
      }
    },
    UserQry() {
      UserQryAction().then(res => {
        this.dataList = res.data;
        this.data = res.data;
      });
    },
    UserAdd() {
      let flag = this.FindUser();
      if (flag) {
        this.$Message.error("编号已经存在！");
        return;
      }
      UserAddAction(this.addFormList).then(res => {
        this.AddBtnNotShow();
        this.UserQry();
        this.$Message.success("增加成功！");
      });
    },
    UserUpdate() {
      UserUpdateAction(this.editFormList).then(res => {
        this.EditBtnNotShow();
        this.UserQry();
        this.$Message.success("修改成功！");
      });
    },
    UserDel(item) {
      UserDelAction(item.no).then(res => {
        this.UserQry();
        this.$Message.success("删除成功！");
      });
    },
    HandleSubmit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          if (this.isAddShow) {
            this.UserAdd();
          } else if (this.isEditShow) {
            this.UserUpdate();
          }
        } else {
          this.$Message.error("填写信息错误!");
        }
      });
    },
    AddBtnShow() {
      this.HandleReset("addFormList");
      this.isAddShow = true;
    },
    AddBtnNotShow() {
      this.isAddShow = false;
    },
    EditBtnShow(item) {
      this.isEditShow = true;
      this.editFormList.no = item.no;
      this.editFormList.name = item.name;
      this.editFormList.email = item.email;
    },
    EditBtnNotShow() {
      this.isEditShow = false;
    },
    HandleReset(name) {
      this.$refs[name].resetFields();
    },
    FindUser() {
      let flag = false;
      this.dataList.forEach(item => {
        if (item.no == this.addFormList.no) flag = true;
      });
      return flag;
    }
  },
  mounted() {
    this.UserQry();
  }
};
</script>
<style>
.out-layer {
  margin: 20px;
}

.right {
  margin: 15px;
}
</style>
