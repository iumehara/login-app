import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import { Link } from 'react-router'
import localStorage from 'localStorage'
import App from '../src/js/App'

describe('App', () => {
  it('renders header', () => {
    let app = shallow(<App/>)

    let links = app.find(Link)
    expect(links.length).toBe(3)
    expect(links.nodes).toInclude(<Link to='/'>Home</Link>)
    expect(links.nodes).toInclude(<Link to='/users'>Users</Link>)
    expect(links.nodes).toInclude(<Link to='/login'>Sign in</Link>)
  })

  it('renders header with logout link and username if logged in', () => {
    localStorage.token = 'token'
    localStorage.username = 'adam'

    let app = shallow(<App/>)

    let links = app.find(Link)
    expect(links.length).toBe(2)
    expect(app.find('a.logout').length).toBe(1)
    expect(app.find('span.username').text()).toInclude('adam')
  })
})
