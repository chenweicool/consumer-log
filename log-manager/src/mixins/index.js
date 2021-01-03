/* eslint-disable */
export const mixin = {
    methods:{
        // 提示信息
        notify(title,type){
            this.$notify({
                title: title,
                type: type
            })
        },

        // 根据相对地址返回绝对地址
        getUrl(url){
            return `${this.$store.state.HOST}/${url}`
        },
         
        // 根据url得到七牛云的对象存储的地址
        getPicUrl(url){
            return `http://cww.chenweiwei.top/${url}`
        },

        // 获取性别中文
        changeSex(value){
           if(value == 0){
               return '女';
           }
           if(value == 1){
               return '男';
           }
           if(value == 3){
               return '组合';
           }
        },

        //改变生日的格式
        attacheBirth(val){
            return String(val).substr(0,10);
        },

        // 上传图片的校验
        beforeAvatarUpload(file){
            const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
            if(!isJPG){
                this.$message.error('上传头像的格式不正确，只能是jpg和png格式');
                return false;
            }

            const isSize = (file.size/1024/1024) < 2;
            if(!isSize){
                this.$message.error('上传文件的大小不能超过2M');
                return false 
            }
            return true;
        },
        
        // 上传成功后的照片
        handleAvatarSuccess(res,file){
            let _this = this;
            if(res.code == 1){
                _this.getData();
                _this.$notify({
                    title: '文件上传成功',
                    type: 'success'
                })
            }else{
                _this.$notify({
                    title: '上传失败',
                    type: 'error'
                })
            }
        },
         // 弹出删除的窗口
        handleDelete(id){
            this.delVisible = true;
            this.idx = id;
        },

        // 把已经赋值的项赋值给multipleSelection
        handleSelectionChange(val){
             this.multipleSelection = val;
        },

        // 这里的弹窗需要优化一下。
        delAll(){
            for(let item of this.multipleSelection){
                this.handleDelete(item.id);

                // 删除每一行
                this.deleteRow();
            }
            this.multipleSelection = [];  // 将选择的文件清空
        },
    }
}
