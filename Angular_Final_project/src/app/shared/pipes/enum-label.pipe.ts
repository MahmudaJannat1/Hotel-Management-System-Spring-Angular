import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'enumLabel'
})
export class EnumLabelPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
