import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import { Link } from 'react-router'
import auth from '../src/js/auth'
import App from '../src/js/App'

describe('App', () => {
  beforeEach(() => auth.deleteSession())

  describe('when not logged in', () => {
    it('renders correct title with correct number of links', () => {
      const app = shallow(<App router={{location: {pathname: '/'}}}/>)

      const header = app.find('header')
      expect(header.node.props.children.length).toBe(5)
      expect(header.find('h2').text()).toBe('login app')
    })

    it('renders correct links when on home path', () => {
      const app = shallow(<App router={{location: {pathname: '/'}}}/>)

      const listItems = app.find('header').node.props.children
      expect(listItems).toInclude(<div className='current'>home</div>)
      expect(listItems).toInclude(<Link to='/users'>users</Link>)
      expect(listItems).toInclude(<Link to='/login'>sign in</Link>)
    })

    it('renders correct links when on users path', () => {
      const app = shallow(<App router={{location: {pathname: '/users'}}}/>)

      const listItems = app.find('header').node.props.children
      expect(listItems).toInclude(<Link to='/'>home</Link>)
      expect(listItems).toInclude(<div className='current'>users</div>)
      expect(listItems).toInclude(<Link to='/login'>sign in</Link>)
    })

    it('renders correct links when on profile path', () => {
      const app = shallow(<App router={{location: {pathname: '/login'}}}/>)

      const listItems = app.find('header').node.props.children
      expect(listItems).toInclude(<Link to='/'>home</Link>)
      expect(listItems).toInclude(<Link to='/users'>users</Link>)
      expect(listItems).toInclude(<div className='current'>sign in</div>)
    })

    it('renders user div when on other user page', () => {
      const app = shallow(
        <App
          router={{location: {pathname: '/bob'}}}
          params={{username: 'bob'}}
        />
      )

      const listItems = app.find('header').node.props.children
      expect(listItems).toInclude(<Link to='/'>home</Link>)
      expect(listItems).toInclude(<Link to='/users'>users</Link>)
      expect(listItems).toInclude(<Link to='/login'>sign in</Link>)
      expect(listItems).toInclude(<div className='current'>bob</div>)
    })
  })

  describe('when logged in', () => {
    it('renders header with logout link and username if logged in', () => {
      auth.setSession({username: 'adam', token: 'token'})

      let app = shallow(<App router={{location: {pathname: '/'}}}/>)

      let listItems = app.find('header').node.props.children
      expect(listItems).toInclude(<div className='current'>home</div>)
      expect(listItems).toInclude(<Link to='/users'>users</Link>)
      expect(listItems).toInclude(<Link to='/users/adam'>your profile (adam)</Link>)
    })
  })
})
