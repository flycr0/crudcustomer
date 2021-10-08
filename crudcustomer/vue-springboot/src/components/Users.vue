<template>
  <div class="container">
    <h3>Informasi Customer</h3>
    <div v-if="message" class="alert alert-success">{{ this.message }}</div>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
           
            <th>Nama Pertama</th>
            <th>Nama Keluarga</th>
            <th>Alamat E-mail</th>
            <th>Edit</th>
            <th>Hapus</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="users in User" v-bind:key="users.id">
          
            <td>{{ users.NamaPertama }}</td>
            <td>{{ users.NamaKeluarga }}</td>
            <td>{{ users.email }}</td>
            <td>
              <button class="btn btn-warning" v-on:click="UpdateUser(user.id)">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-danger" v-on:click="DeleteUser(user.id)">
               Hapus
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="row">
        <button class="btn btn-success" v-on:click="AddUser()">Tambah</button>
      </div>
    </div>
  </div>
</template>
<script>
import UserDataService from "../service/UserDataService";

export default {
  name: "Users",
  data() {
    return {
      users: [],
      message: "",
    };
  },
  methods: {
    RefreshUsers() {
      UserDataService.RetrieveAllUsers().then((res) => {
        this.users = res.data;
      });
    },
    AddUser() {
      this.$router.push(`/user/-1`);
    },
    UpdateUser(id) {
      this.$router.push(`/user/${id}`);
    },
    DeleteUser(id) {
      UserDataService.DeleteUser(id).then(() => {
        this.RefreshUsers();
      });
    },
  },
  created() {
    this.RefreshUsers();
  },
};
</script>
