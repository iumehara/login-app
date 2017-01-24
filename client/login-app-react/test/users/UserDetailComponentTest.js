import expect from 'expect'
import { shallow, mount } from 'enzyme'
import React from 'react'
import UserDetailComponent from '../../src/js/users/UserDetailComponent'
import UserDetail from '../../src/js/users/UserDetail'
import * as fetcher from '../../src/js/fetcher'

describe('UserDetailComponenet', () => {
  beforeEach(() => expect.restoreSpies())

  it('displays user details', () => {
    expect.spyOn(UserDetailComponent.prototype, 'componentWillMount')

    let userDetailComponent = shallow(<UserDetailComponent params={{username: 'adam'}}/>)
    userDetailComponent.setState({user: {id: 1, username: 'adam', role: 'staff'}})

    expect(userDetailComponent.find('UserDetail').length).toEqual(1)
  })

  it('requests user and sets to state', () => {
    const stubGetUserData = () => {
      return {
        then: (setStateImplementation) => {
          setStateImplementation(
            {id: 1, username: 'adam', role: 'staff'}
          )
        }
      }
    }

    expect.spyOn(fetcher, 'getUser').andReturn(stubGetUserData())

    const userDetailComponent = shallow(<UserDetailComponent params={{username: 'adam'}}/>)

    expect(userDetailComponent.instance().state).toEqual({
      user: {id: 1, username: 'adam', role: 'staff'},
      username: 'adam'
    })
  })

  it('makes new request if path is updated with new username', () => {
    let getUserAndSetToStateSpy = expect.spyOn(
      UserDetailComponent.prototype, 'getUserAndSetToState'
    )

    let userDetailComponent = mount(<UserDetailComponent params={{username: 'adam'}}/>)

    expect(getUserAndSetToStateSpy).toHaveBeenCalled()

    userDetailComponent.setProps({params: { username: 'bob' }})

    expect(getUserAndSetToStateSpy.calls.length).toEqual(2)
  })
})
