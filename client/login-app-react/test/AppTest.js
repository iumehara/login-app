import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import { Link } from 'react-router'
import localStorage from 'localStorage'
import App from '../src/js/App'

describe('App', () => {
  it('renders header', () => {
    let app = shallow(<App/>)

    expect(app.find(Link).length).toBe(3)
    expect(app.find(Link).nodes).toInclude(<Link to='/login'>Sign in</Link>)
    expect(app.find(Link).nodes).toInclude(<Link to='/about'>About</Link>)
    expect(app.find(Link).nodes).toInclude(<Link to='/users'>Users</Link>)
  })

  it('renders header with logout link if logged in', () => {
    localStorage.token = 'token'

    let app = shallow(<App/>)

    expect(app.find(Link).length).toBe(3)
    expect(app.find(Link).nodes).toInclude(<Link to='/logout'>Log out</Link>)
  })
})
