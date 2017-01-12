import expect from 'expect'
import { shallow, mount } from 'enzyme'
import React from 'react'
import UserDetailComponent from '../../src/js/users/UserDetailComponent'

describe('UserDetail', () => {
  it('renders  user name', () => {
    let stubGetUserData = () => {
      return {
        then: (setStateImplementation) => {
          setStateImplementation(
            {id: 1, username: 'adam', role: 'staff'}
          )
        }
      }
    }

    let spy = expect.spyOn(
      UserDetailComponent.prototype, 'getUser'
    ).andReturn(
      stubGetUserData()
    )

    let userDetailComponent = shallow(<UserDetailComponent params={{username: 'adam'}}/>)

    expect(userDetailComponent.find('.username').text()).toInclude('Username: adam')
    expect(userDetailComponent.find('.role').text()).toInclude('Role: staff')
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
