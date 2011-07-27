var mongoose = require('mongoose')
  , dbref = require("mongoose-dbref")
  , utils = dbref.utils
  , Schema = mongoose.Schema
  , ObjectId = Schema.ObjectId
  , DBRef = mongoose.SchemaTypes.DBRef;


var Person = new Schema({
    id : ObjectId
    , nickName : { type: String, index: { unique: true } }
    , primary : Boolean 
    , language : { type : String, enum : ['english', 'dutch', 'german'], default : 'english'}
    , birthday : { type: Date, default: '12/10/1990' }
    , gender : { type: String, enum: ['male', 'female'], default : 'male'}
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
   , profiles : [DBRef]
});

mongoose.model('Person', Person);
mongoose.model('Account', Account);