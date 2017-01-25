import expect from 'expect'
import { shallow, mount } from 'enzyme'
import React from 'react'
import UsersComponent from '../../src/js/users/UsersComponent'
import UsersTable from '../../src/js/users/UsersTable'
import * as fetcher from '../../src/js/fetcher'

describe('UsersComponent', () => {
  afterEach(() => expect.restoreSpies())

  describe('display', () => {
    it('displays a UsersTable', () => {
      expect.spyOn(UsersComponent.prototype, 'componentWillMount')

      const usersComponent = shallow(<UsersComponent/>)
      usersComponent.instance().setState({users: [{id: 1, username: 'adam', role: 'staff'}]})

      expect(usersComponent.find(UsersTable).length).toBe(1)
    })
  })

  describe('functions', () => {
    describe('getUsersAndSetToState', () => {
      it('saves users returned from request to state', () => {
        expect.spyOn(
          fetcher, 'getUsers'
        ).andReturn({
          then: (promiseResolver) => promiseResolver([{id: 1, username: 'adam', role: 'staff'}])
        })

        expect.spyOn(UsersComponent.prototype, 'componentWillMount')

        const usersComponent = shallow(<UsersComponent/>)

        usersComponent.instance().getUsersAndSetToState({})

        expect(usersComponent.instance().state.users).toEqual([
          {id: 1, username: 'adam', role: 'staff'}
        ])
      })

      describe('createUserAndSetToState', () => {
        it('saves created user returned from request to state', () => {
          expect.spyOn(
            fetcher, 'postUser'
          ).andReturn({
            then: (promiseResolver) => promiseResolver({id: 3, username: 'cam', role: 'admin'})
          })

          expect.spyOn(UsersComponent.prototype, 'componentWillMount')

          const usersComponent = shallow(<UsersComponent/>)

          usersComponent.instance().setState({
            users: [
              {id: 1, username: 'adam', role: 'staff'},
              {id: 2, username: 'bob', role: 'staff'}
            ]
          })

          usersComponent.instance().createUserAndSetToState({})

          expect(usersComponent.instance().state.users).toEqual([
            {id: 1, username: 'adam', role: 'staff'},
            {id: 2, username: 'bob', role: 'staff'},
            {id: 3, username: 'cam', role: 'admin'}
          ])
        })
      })
    })
  })

  describe('mounting behaviour', () => {
    it('gets users and sets to state', () => {
      let getUsersAndSetToStateSpy = expect.spyOn(UsersComponent.prototype, 'getUsersAndSetToState')

      const usersComponent = shallow(<UsersComponent/>)

      expect(getUsersAndSetToStateSpy).toHaveBeenCalled()
    })
  })
})
