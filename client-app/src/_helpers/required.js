export default value => (value || typeof value === 'number' ? undefined : 'Required')
