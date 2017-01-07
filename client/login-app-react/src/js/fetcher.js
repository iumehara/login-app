import localStorage from 'localStorage'

export const get = (resource) => {
  console.log('---- local', localStorage)

  return fetch(pathForResource(resource), {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.token}`
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
