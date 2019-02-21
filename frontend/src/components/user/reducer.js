import { actions } from './actions';

const initState = {
  isAuthenticated: false
}

export default function (state = initState, action) {
  switch (action.type) {
    case actions.LOGIN_REQUEST:
      return {
        ...state,
        user: action.payload
      }
    case actions.LOGIN_SUCCESS:
      return { 
        ...state, 
        user: action.payload
      };
    case actions.LOGIN_FAILURE:
      return { 
        ...state, 
        user: action.payload
      };
    case actions.LOGOUT_REQUEST:
      return {
        ...state,
        user: action.payload
      }
    case actions.LOGOUT_SUCCESS:
      return { 
        ...state,
        user: action.payload
      };
    case actions.LOGOUT_FAILURE:
      return state
    default:
      return state
  }
}
