import {FormGroup} from '@angular/forms';

export function markAllFormFieldAsDirty(form: FormGroup) {
  for (const field in form.controls) {
    if (form.controls.hasOwnProperty(field)) {
      form.controls[field].markAsDirty();
      form.controls[field].markAsTouched();
    }
  }
}

export function markAllFormFieldAsPristine(form: FormGroup) {
  for (const field in form.controls) {
    if (form.controls.hasOwnProperty(field)) {
      form.controls[field].markAsPristine();
      form.controls[field].markAsUntouched();
    }
  }
}
