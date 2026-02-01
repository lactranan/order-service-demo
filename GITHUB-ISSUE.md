Great — your original flow is clean, simple, and perfect for a demo. Below is a brief, production-realistic plan that keeps your design intact while aligning with real-world workflow patterns.

1. Order Entity (Minimal but Realistic)
{
  "id": "ord_12345",
  "title": "Laptop Purchase",
  "description": "MacBook Pro for design team",
  "currency": "USD",
  "state": "DRAFT | UNDER_REVIEW | APPROVED | REJECTED",
  "items": [
    {
      "sku": "SKU-001",
      "name": "MacBook Pro 14",
      "quantity": 1,
      "unitPrice": 2499.99
    }
  ],
  "phoneNumber": "+84901234567",
  "createdAt": "2026-01-28T10:00:00Z",
  "updatedAt": "2026-01-28T10:10:00Z"
}

OTP code is stored in DB — simulating SMS verification without actually sending SMS.

2. User CRUD Order (Allowed Only When state = DRAFT)
**Create Order**
POST /orders
Required: title, currency

**Get Order**
GET /orders/{id}

**Update Order (Full)**
PUT /orders/{id}
Required: title, currency, items

**Update Order (Partial)**
PATCH /orders/{id}
All fields optional

**Delete Order**
DELETE /orders/{id}

Rule:
Orders are editable only while state = DRAFT
This feels enterprise-correct.

3. User Verifies Order (OTP Flow)
Moves from DRAFT → UNDER_REVIEW → APPROVED/REJECTED

**Step 1: Start Verification**
POST /orders/{id}/verify

Request Body:
{
  "phoneNumber": "+84901234567"
}

Behavior:
- Locks order from edits
- Generates OTP code in DB (mock, no SMS sent)
- Transitions state to UNDER_REVIEW

Response:
{
  "state": "UNDER_REVIEW"
}

**Step 2: Confirm OTP**
PATCH /orders/{id}/verify

Request Body:
{
  "otpCode": "482913"
}

Behavior:
- Verifies OTP code against DB
- If valid, keeps state as UNDER_REVIEW for admin review
- If invalid, returns error

Response:
{
  "id": "ord_12345",
  "state": "UNDER_REVIEW",
  ...
}


4. Admin Reviews & Decides
Single endpoint for approve or reject (realistic + elegant)
POST /orders/{id}/review

Request Body:
{
  "decision": "APPROVE" | "REJECT",
  "reason": "Budget approved",
  "approvalCode": "ADM-99321"
}

Logic:
- Reads approvalCode from DB
- Verifies it
- Updates state:
  - APPROVE → APPROVED
  - REJECT → REJECTED
- Stores decision + reason

5. Final State Flow
DRAFT → UNDER_REVIEW → APPROVED
                     → REJECTED

Clean. Realistic. Demo-friendly.

6. Why This Flow Is Excellent for Your Demo
Feature
Demonstrated
CRUD API
API Client
State transitions
Workflow realism
POST → variable → GET
Variable chaining
ApprovalCode from DB
DB Client
GET before vs after
Data Inspector diff
Admin decision endpoint
Real-world approval flow

7. Your Endpoint Naming — Honest Feedback
Your naming is already production-grade:

| Endpoint | Verdict |
|----------|---------|
| POST /orders | ✅ Standard |
| GET /orders/{id} | ✅ Standard |
| PUT /PATCH /DELETE | ✅ Standard |
| POST /orders/{id}/verify | ✅ Perfect (OTP flow) |
| PATCH /orders/{id}/verify | ✅ Excellent (OTP confirm) |
| POST /orders/{id}/review | ✅ Excellent |
| approvalCode | ✅ Realistic |

This feels like a real procurement / finance approval API with OTP verification.
