<template>
  <div>
    <h3>User Data</h3>
    <div class="container">
      <form @submit="validateAndSubmit">
        <div v-if="errors.length">
          <div
            class="alert alert-danger"
            v-bind:key="index"
            v-for="(error, index) in errors"
          >
            {{ error }}
          </div>
        </div>
        <fieldset class="form-group">
          <label>Nama Pertama</label>
          <input type="text" class="form-control" v-model="NamaPertama" />
        </fieldset>
        <fieldset class="form-group">
          <label>Nama Keluarga</label>
          <input type="text" class="form-control" v-model="NamaKeluarga" />
        </fieldset>
        <fieldset class="form-group">
          <label>E-mail</label>
          <input type="text" class="form-control" v-model="email" />
        </fieldset>
        <button class="btn btn-success" type="submit">Simpan Data</button>
      </form>
    </div>
  </div>
</template>
<script>
import UserDataService from "../service/UserDataService";

export default {
  name: "User",
  data() {
    return {
      NamaPertama: "",
      NamaKeluarga: "",
      email: "",
      errors: [],
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
  },
  methods: {
    RefreshUserDetails() {
      UserDataService.RetrieveUser(this.id).then((res) => {
        this.NamaPertama = res.data.NamaPertama;
        this.NamaKeluarga = res.data.NamaKeluarga;
        this.email = res.data.email;
      });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.NamaPertama) {
        this.errors.push("Nama pertama tidak boleh kosong");
      } 
      if (!this.NamaKeluarga) {
        this.errors.push("Nama keluarga tidak boleh kosong");
      } 
      if (!this.email) {
        this.errors.push("Periksa e-mail");
      } 
      if (this.errors.length === 0) {
        if (this.id == -1) {
          UserDataService.CreateUser({
            NamaPertama: this.NamaPertama,
            NamaKeluarga: this.NamaKeluarga,
            email: this.email,
          }).then(() => {
            this.$router.push("/users");
          });
        } else {
          UserDataService.UpdateUser(this.id, {
            id: this.id,
             NamaPertama: this.NamaPertama,
            NamaKeluarga: this.NamaKeluarga,
            email: this.email,
          }).then(() => {
            this.$router.push("/users");
          });
        }
      }
    },
  },
  created() {
    this.RefreshUserDetails();
  },
};
</script>
