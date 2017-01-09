import auth from './auth'

export const get = (token, resource, id) => {
  return fetch(pathForResource(resource, id), {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
  }).then((response) => {
    return response.json()
  })
}

export const post = (resource, body) => {
  return fetch(pathForResource(resource), {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(body)
  }).then((response) => {
    return response.json()
  })
}

const pathForResource = (resource, id) => {
    switch (resource) {
      case 'login':
        return 'http://localhost:8080/login'
        break;
      case 'user':
        return `http://localhost:8080/users/${id}`
        break;
      default:
        return 'http://localhost:8080/users'
    }
}
