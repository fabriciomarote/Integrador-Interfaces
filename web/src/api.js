var Api = {
    login: function() { return axios.post('http://localhost:7000/login', data)},
    register: function() { return axios.post('http://localhost:7000/register', data)},
    getUser: function() { return axios.get('http://localhost:7000/user', data)},
    addNote: function()  { return axios.post('http://localhost:7000/note', data)},
    editNote: function(id)  { return axios.put('http://localhost:7000/note/${id}', data)},
    deleteNote: function(id)  { return axios.delete('http://localhost:7000/note/${id}', data)},
}

// Asumo que el puerto es 7000