export const saveAuthData = (token, user, roles) => {
    localStorage.setItem("token", token);
    localStorage.setItem("user", JSON.stringify(user));
    localStorage.setItem("roles", JSON.stringify(roles));
  };

  export const getToken = () => {
    return localStorage.getItem("token");
  };