/*
 * This is an Android user space port of DVB-T Linux kernel modules.
 *
 * Copyright (C) 2017 Martin Marinov <martintzvetomirov at gmail com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package info.martinmarinov.drivers;

import android.hardware.usb.UsbDevice;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class DeviceFilter implements Serializable {
    private final int vendorId;
    private final int productId;
    private final String name;

    public DeviceFilter(int vendorId, int productId, String name) {
        this.vendorId = vendorId;
        this.productId = productId;
        this.name = name;
    }

    public boolean matches(@NonNull UsbDevice usbDevice) {
        return usbDevice.getVendorId() == vendorId && usbDevice.getProductId() == productId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceFilter that = (DeviceFilter) o;

        if (vendorId != that.vendorId) return false;
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        int result = vendorId;
        result = 31 * result + productId;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
