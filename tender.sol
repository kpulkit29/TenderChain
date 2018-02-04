pragma solidity ^0.4.17;
contract Tender {
   
   struct bals{
       uint balance;
   }
   mapping(uint=>bals) rem_Bal;
   address[]  adds;
   uint256[] balances;
   event rfund(address _from,uint amt_added);
   event received(address _to,uint256 amt);
   function getKeepers() view public returns(address[]){
       return adds;
   }
   function setBalance(uint id) public {
       rem_Bal[id]=bals(0);
   }
 
   function send_money(uint index,uint256 amt,address _to) public {
       bool sent=_to.send(amt);
       bals obj=rem_Bal[index];
       received(_to,amt);
       obj.balance+=amt;
   }
   
   function() payable {
       if(msg.value>0){
           rfund(msg.sender,msg.value);
       }
   }
}