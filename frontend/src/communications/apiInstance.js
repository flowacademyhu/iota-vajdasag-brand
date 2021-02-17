import axios from "axios";

const api = axios.create({
  baseURL: `/api/`,
});

api.interceptors.request.use(
  (request) => {
    const token = sessionStorage.getItem("token");
    if (token) {
      request.headers = {
        ...request.headers,
        Authorization: "Bearer " + token,
      };
    }
    return request;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
