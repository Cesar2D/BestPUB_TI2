function showPaymentDetails() {
    var paymentSelect = document.getElementById("payment");
    var cardDetails = document.getElementById("card-details");
  
    if (paymentSelect.value === "credit-card" || paymentSelect.value === "debit-card") {
      cardDetails.style.display = "block";
    } else {
      cardDetails.style.display = "none";
    }
  }
  
  function openCardDetails() {
    var payment = document.getElementById("payment").value;
  
    if (payment === "credit-card" || payment === "debit-card") {
      window.open("card-details.html", "_blank");
    } else {
      savePayment();
    }
  }
  
  function savePayment() {
    var nameInput = document.getElementById("name");
    var paymentSelect = document.getElementById("payment");
    var cardNumberInput = document.getElementById("card-number");
    var cardNameInput = document.getElementById("card-name");
    var cpfInput = document.getElementById("cpf");
    var expirationDateInput = document.getElementById("expiration-date");
    var cvvInput = document.getElementById("cvv");
    var cardNicknameInput = document.getElementById("card-nickname");
  
    var name = nameInput.value;
    var payment = paymentSelect.value;
    var cardNumber = cardNumberInput ? cardNumberInput.value : "";
    var cardName = cardNameInput ? cardNameInput.value : "";
    var cpf = cpfInput ? cpfInput.value : "";
    var expirationDate = expirationDateInput ? expirationDateInput.value : "";
    var cvv = cvvInput ? cvvInput.value : "";
    var cardNickname = cardNicknameInput ? cardNicknameInput.value : "";
  
    if (name && payment) {
      var paymentItem = {
        name: name,
        payment: payment,
        cardNumber: cardNumber,
        cardName: cardName,
        cpf: cpf,
        expirationDate: expirationDate,
        cvv: cvv,
        cardNickname: cardNickname
      };
  
      var savedItems = localStorage.getItem("paymentItems");
      if (savedItems) {
        var items = JSON.parse(savedItems);
        items.push(paymentItem);
        localStorage.setItem("paymentItems", JSON.stringify(items));
      } else {
        localStorage.setItem("paymentItems", JSON.stringify([paymentItem]));
      }
  
      clearForm();
      showPaymentItems();
    }
  }
  
  function clearForm() {
    document.getElementById("name").value = "";
    document.getElementById("payment").value = "";
    document.getElementById("card-number").value = "";
    document.getElementById("card-name").value = "";
    document.getElementById("cpf").value = "";
    document.getElementById("expiration-date").value = "";
    document.getElementById("cvv").value = "";
    document.getElementById("card-nickname").value = "";
  }
  
  function showPaymentItems() {
    var paymentItemsList = document.getElementById("payment-items");
    paymentItemsList.innerHTML = "";
  
    var savedItems = localStorage.getItem("paymentItems");
    if (savedItems) {
      var items = JSON.parse(savedItems);
  
      items.forEach(function(item) {
        var li = document.createElement("li");
        li.textContent = item.name + " - " + item.payment;
        paymentItemsList.appendChild(li);
      });
    }
  }
  
        function clearData() {
          localStorage.clear();
          alert("Os dados foram removidos.");
        }
      
  
  
  showPaymentItems();