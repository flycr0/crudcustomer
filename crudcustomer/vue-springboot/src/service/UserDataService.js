import axios from 'axios'
const USER_API_URL = 'http://localhost:8080/api/v1'

class UserDataService {
    RetrieveAllUsers() {
        return axios.get(`${USER_API_URL}/users`);
    }

    RetrieveUser(id) {
        return axios.get(`${USER_API_URL}/users/${id}`);
    }


    DeleteUser(id) {
        return axios.delete(`${USER_API_URL}/users/${id}`);
    }


    UpdateUser(id, user) {
        return axios.put(`${USER_API_URL}/users/${id}`, user);
    }


    CreateUser(user) {
        return axios.post(`${USER_API_URL}/users`, user);
    }
}
