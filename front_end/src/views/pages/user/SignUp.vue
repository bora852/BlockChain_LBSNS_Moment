<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-80 md-small-size-100"
            style="margin: 0 auto; background: linear-gradient(45deg, #FFFF, transparent);"
          >
            <tabs
              :tab-name="['회원등록', '프로필등록', '가입하기']"
              :tab-icon="['supervisor_account', 'person_pin', 'check']"
              plain
              nav-pills-icons
              color-button="info"
            >
              <template slot="tab-pane-1">
                <div
                  class="card md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100 paddingSingUp"
                  style="background:white;"
                >
                  <div style="font-size:0.9em; text-align:right;">*는 필수 입력 항목입니다.</div>
                  <md-field>
                    <label>* 이메일</label>
                    <md-input v-model="email" type="text"></md-input>
                    <md-button class="md-rose md-round" v-on:click="idDuplicateChk">중복 체크</md-button>
                  </md-field>
                  <md-field>
                    <label>* 닉네임</label>
                    <md-input v-model="nickname" type="text"></md-input>
                  </md-field>
                  <md-field>
                    <label>* 이름</label>
                    <md-input v-model="name" type="text"></md-input>
                  </md-field>
                  <md-field>
                    <label>* 비밀번호</label>
                    <md-input v-model="password" type="password"></md-input>
                  </md-field>
                  <md-field>
                    <label>* 비밀번호 확인</label>
                    <md-input v-model="passwordcheck" type="password"></md-input>
                  </md-field>
                  <md-field>
                    <label>* 휴대폰</label>
                    <md-input v-model="phone" type="text"></md-input>
                  </md-field>

                  <md-datepicker v-model="birthday">
                    <label>* 생년월일</label>
                  </md-datepicker>
                  <br />
                  <div>
                    <span style="margin-right:10px">* 성별 :</span>
                    <md-radio v-model="gender" value="M">남자</md-radio>
                    <md-radio v-model="gender" value="W">여자</md-radio>
                  </div>
                </div>
              </template>

              <template slot="tab-pane-2">
                <div
                  class="card md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100"
                  style="background:white; padding:20px;"
                >
                  <label>사진등록</label>
                  <br />
                  <div class="ml-2 col-sm-6">
                    <img :src="img" id="preview" class="img-thumbnail" style="float: left;" />
                  </div>
                  <div class="ml-2 col-sm-6">
                    <div id="msg"></div>
                    <form method="post" id="image-form">
                      <input
                        type="file"
                        ref="inputRef"
                        name="img[]"
                        class="file"
                        accept="image/*"
                        @change="changeFile($event)"
                        style="display:none"
                      />
                      <div class="input-group my-3">
                        <input
                          type="text"
                          class="form-control"
                          disabled
                          placeholder="Upload File"
                          v-model="fileName"
                        />
                        <div class="input-group-append">
                          <button
                            type="button"
                            class="browse btn btn-primary"
                            v-on:click="uploadImg()"
                          >Browse...</button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <md-field>
                    <label>소개글</label>
                    <md-textarea v-model="aboutme"></md-textarea>
                  </md-field>
                  <md-field>
                    <label>인스타그램</label>
                    <md-input v-model="instagram" type="text"></md-input>
                  </md-field>
                  <md-field>
                    <label>페이스북</label>
                    <md-input v-model="facebook" type="text"></md-input>
                  </md-field>
                  <br />
                </div>
              </template>
              <template slot="tab-pane-3">
                <div
                  class="card md-layout-item md-size-100 md-xsmall-size-100 md-small-size-100 md-medium-size-100  "
                  style="background:white; padding:20px;" 
                >
                  <p>이메일 본인 인증 후 서비스 사용이 가능합니다.</p>
                  <div class="alignCenter">
                    <md-button type="submit" class="md-size-30 md-small-size-50 md-layout-item md-rose md-round" v-on:click="signUp">이메일 인증하기</md-button>
                  </div>
                </div>
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { Tabs } from "@/components";
import { log } from "util";
import UserService from "@/services/UserService.js";
import EthereumService from "@/services/EthereumService.js";

export default {
  components: {
    LoginCard,
    Tabs
  },
  bodyClass: "login-page",
  data() {
    return {
      nickname: "",
      name: "",
      password: "",
      passwordcheck: "",
      phone: "",
      email: "",
      fileName: "프로필등록..",
      imgName: "",
      img: "https://placehold.it/150x150",
      extension: "",
      birthday: new Date(),
      gender: "",
      aboutme: "",
      facebook: "",
      instagram: "",
      duplChk: false
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/signup_background.jpg")
    }
  },
  methods: {
    uploadImg() {
      this.$refs.inputRef.click();
    },
    changeFile(event) {
      //파일 이름 생성 = 이메일아이디+랜덤값
      let eamilLen = this.email.lastIndexOf("@");
      let random = Math.floor(Math.random() * 100000);
      this.imgName = this.email.substring(0, eamilLen) + "_" + random;

      // console.log(this.imgName);

      this.fileName = event.target.files[0].name;
      let fileLen = this.fileName.length;
      let lastDot = this.fileName.lastIndexOf(".");
      this.extension = this.fileName.substring(lastDot, fileLen).toLowerCase();

      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = e => {
        this.img = e.target.result;
      };
    },
    signUp() {
      var scope = this;
      //빈공간 체크 등
      if (scope.email == "") {
        alert("이메일을 입력해 주세요.");
        return false;
      }

      if (scope.nickname == "") {
        alert("닉네임을 입력해 주세요.");
        return false;
      }

      if (scope.name == "") {
        alert("이름을 입력해 주세요.");
        return false;
      }

      if (scope.password == "") {
        alert("비밀번호를 입력해 주세요.");
        return false;
      }

      if (scope.passwordcheck == "") {
        alert("비밀번호를 입력해 주세요.");
        return false;
      }

      if (scope.phone == "") {
        alert("핸드폰 번호를 입력해 주세요.");
        return false;
      }

      if (scope.gender == "") {
        alert("성별을 선택해 주세요.");
        return false;
      }

      //유효성 체크
      if (scope.password !== scope.passwordcheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
      }

      if (!scope.duplChk) {
        alert("이메일 중복체크를 해주세요.");
        email.focus();
        return false;
      }

      // 계좌생성
      EthereumService.newAccount(scope.password, walletAddress => {
        //회원 가입
        UserService.signUp({
          email: scope.email,
          birthday: scope.birthday,
          gender: scope.gender,
          nickname: scope.nickname,
          userName: scope.name,
          phone: scope.phone,
          password: scope.password,
          sns1: scope.facebook,
          sns2: scope.instagram,
          about: scope.aboutme,
          walletAddress: walletAddress
        }).then(userdata => {
          if (userdata.data == 1) {
            let imgName = scope.imgName == "" ? "이미지없음" : scope.imgName;
            let imgData = scope.img;
            let extension =
              scope.extension == "" ? "이미지없음" : scope.extension;

            UserService.uploadImage({
              imgName: imgName,
              imgData: imgData,
              extension: extension,
              email: scope.email
            }).then(uploadImgData => {
              alert("이메일이 발송되었습니다. 본인 인증 후 서비스 사용이 가능합니다.");
              scope.$router.push("/");
            });
          }
        });
      });
    },
    idDuplicateChk() {
      let scope = this;
      if (scope.email != "") {
        UserService.getUserByEmail(scope.email)
          .then(result => {
            // console.log("result : ",result);
            if (result.data == "") {
              scope.duplChk = true;
              alert("사용가능한 이메일입니다.");
            } else {
              scope.duplChk = false;
              alert("이미 존재하는 이메일입니다.");
            }
          })
          .catch(err => {
            alert("에러 에러 에러");
          });
      } else {
        alert("아이디를 입력해주세요.");
      }
    }
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url("https://images.unsplash.com/photo-1432821596592-e2c18b78144f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80")`
      };
    }
  }
};
</script>

<style lang="css">
@import url("../../../assets/bootstrap/bootstrap.css");

@media (min-width: 481px) {
  .paddingSingUp {  padding: 40px 100px 20px 100px !important; }
}

/* 모바일*/
@media (max-width: 480px) {
  .alignCenter { text-align: center; }
}
</style>
