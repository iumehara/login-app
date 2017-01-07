export const get = (resource) => {
  return fetch(pathForResource(resource), {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer token'
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

const pathForResource = (resource) => {
    switch (resource) {
      case 'login':
        return 'http://localhost:8080/login'
        break;
      default:
        return 'http://localhost:8080/users'
    }
}
