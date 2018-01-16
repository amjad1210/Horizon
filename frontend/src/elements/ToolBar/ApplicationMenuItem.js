import React, { PureComponent } from 'react'
import PropTypes from 'prop-types'

import { Transition, Header, Icon } from 'semantic-ui-react'

class ApplicationMenuItem extends PureComponent {
    static propTypes = {
      icon: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired,
      toggleAppMenu: PropTypes.func.isRequired
    }

    state = { visible: true }

    render() {
      const { visible } = this.state
      const { icon, name, toggleAppMenu } = this.props

      return (
        <Transition animation='scale' duration={400} visible={visible} onComplete={toggleAppMenu}>
          <Header className='grow' as='h4' icon inverted onClick={() => this.setState({ visible: !visible })}>
            <Icon name={icon}/>
              {name}
          </Header>
        </Transition>
      )
  }
}

export default ApplicationMenuItem
