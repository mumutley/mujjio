var mongoose = require('mongoose')
  , Schema = mongoose.Schema;
  
  
var Account = new Schema({
   email : { type: String, index: { unique: true } }
   , password : String
   , birthday : { type: Date, default: '12/10/1990' }
   , status : {type : String, enum: ['registered', 'valid', 'expired', 'blocked'] }
   , name: {
        givenName: String
        ,familtName: String
   }          
});

var Profile = new Profile({
    language : { type : String
       , enum : ['English', 'Dutch', 'German']
       , default : 'English'}
       
   , gender : { type: String
       , enum: ['male', 'female']
       , default : 'male'}
});

mongoose.model('Account', Account);