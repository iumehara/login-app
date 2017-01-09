import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import { Link } from 'react-router'
import auth from '../src/js/auth'
import App from '../src/js/App'

describe('App', () => {
  beforeEach(() => auth.deleteSession())

  describe('when not logged in', () => {
    it('renders correct header when on home path', () => {
      let app = shallow(<App router={{location: {pathname: '/'}}}/>)

      let listItems = app.find('ul').node.props.children
      expect(listItems.length).toBe(3)
      expect(listItems).toInclude(<li>Home</li>)
      expect(listItems).toInclude(<li><Link to='/users'>Users</Link></li>)
      expect(listItems).toInclude(<li><Link to='/login'>Sign in</Link></li>)
    })
    it('renders correct header when on users path', () => {
      let app = shallow(<App router={{location: {pathname: '/users'}}}/>)

      let listItems = app.find('ul').node.props.children
      expect(listItems.length).toBe(3)
      expect(listItems).toInclude(<li><Link to='/'>Home</Link></li>)
      expect(listItems).toInclude(<li>Users</li>)
      expect(listItems).toInclude(<li><Link to='/login'>Sign in</Link></li>)
    })
    it('renders correct header when on home path', () => {
      let app = shallow(<App router={{location: {pathname: '/login'}}}/>)

      let listItems = app.find('ul').node.props.children
      expect(listItems.length).toBe(3)
      expect(listItems).toInclude(<li><Link to='/'>Home</Link></li>)
      expect(listItems).toInclude(<li><Link to='/users'>Users</Link></li>)
      expect(listItems).toInclude(<li>Sign in</li>)
    })
  })

  describe('when logged in', () => {
    it('renders header with logout link and username if logged in', () => {
      auth.setSession({username: 'adam', token: 'token'})

      let app = shallow(<App router={{location: {pathname: '/'}}}/>)

      let listItems = app.find('ul').node.props.children
      expect(listItems.length).toBe(3)
      expect(listItems).toInclude(<li>Home</li>)
      expect(listItems).toInclude(<li><Link to='/users'>Users</Link></li>)
      expect(app.find('span.username').text()).toInclude('adam')
      expect(app.find('a.logout').length).toBe(1)
    })
  })
})
