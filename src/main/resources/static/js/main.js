
Vue.component('message-row', {
  props: ['message'],
  template: '<div><i>({{ message.id }})</i> {{ message.text }} </div>'
});
Vue.component('messages-list', {
 props: ['messages'],
  template: '<div> <message-row v-for="message in messages" :message="message" /></div>'
});
var app = new Vue({
  el: '#app',
  template: '<messages-list :messages="messages"/>',
  data: {
    messages:[
    {id: '123', text: 'Wow'},
    {id: '124', text: 'Hi'},
    {id: '125', text: 'How are you'},
    {id: '126', text: 'I am fine'},
    {id: '127', text: 'Ok'}
    ]
  }
});