var mongooseTypes = require('mongoose-types')
  , mongoose = require('mongoose')
  , Schema = mongoose.Schema
  , ObjectId = Schema.ObjectId
  , Email = mongoose.SchemaTypes.Email;
  
var Person = new Schema({
	nickName : ObjectId
    , primary : Boolean 
    , language : { type : String
       , enum : ['English', 'Dutch', 'German']
       , default : 'English'}
    , birthday : { type: Date, default: '12/10/1990' }
    , gender : { type: String
       , enum: ['male', 'female']
       , default : 'male'}
    , givenName: String
    , familyName: String
    , displayName : String
    , joined : Date
    , updated : { type: Date, default: Date.now }
});

var Account = new Schema({
   email : { type: String, index: { unique: true } }
   , password : String
   , status : {type : String, enum: ['registered', 'valid', 'expired', 'blocked'] } 
   , created : Date
   , update : { type: Date, default: Date.now }
   , profiles : [Person]
});

mongoose.model('Person', Account);
mongoose.model('Account', Account);